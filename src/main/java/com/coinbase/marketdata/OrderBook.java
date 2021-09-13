package com.coinbase.marketdata;

import com.coinbase.marketdata.log.LogWriter;
import com.coinbase.marketdata.log.SystemOutWriter;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class OrderBook {
    private final Lock askLock = new ReentrantLock();
    private final Lock bidLock = new ReentrantLock();
    private final TreeMap<Object, Object> ask = new TreeMap(new AskComparator());
    private final TreeMap<Object, Object> bid = new TreeMap(new BidComparator());
    private final LogWriter logger = new SystemOutWriter();
    private final int orderBookDepth;
    private final String[] askPrices;
    private final String[] bidPrices;

    public OrderBook(int orderBookDepth) {
        this.orderBookDepth = orderBookDepth;
        this.askPrices = new String[orderBookDepth];
        this.bidPrices = new String[orderBookDepth];
    }

    public void buildOrderBook(L2ChannelData l2ChannelData) {
        ask.putAll(l2ChannelData.getAsks().parallelStream().collect(Collectors.toMap(p -> ((List)p).get(0), p ->((List)p).get(1))));
        bid.putAll(l2ChannelData.getBids().parallelStream().collect(Collectors.toMap(p -> ((List)p).get(0), p ->((List)p).get(1))));
        loadAskPrices();
        loadBidPrices();
        printOrderBook();
    }

    public void updateOrderBook(L2ChannelData l2ChannelData) {
        askLock.lock();
        try {
            ask.putAll(l2ChannelData.getChanges()
                        .parallelStream()
                        .filter(p->((String)((List)p).get(0)).equalsIgnoreCase("sell"))
                        .filter(p-> new BigDecimal((String)((List)p).get(2)).compareTo(BigDecimal.ZERO)>0)
                        .collect(Collectors.toMap(p -> ((List)p).get(1), p ->((List)p).get(2))));
            loadAskPrices();
        } finally {
            askLock.unlock();
        }

        bidLock.lock();
        try {
            bid.putAll(l2ChannelData.getChanges()
                    .parallelStream()
                    .filter(p->((String)((List)p).get(0)).equalsIgnoreCase("buy"))
                    .filter(p-> new BigDecimal((String)((List)p).get(2)).compareTo(BigDecimal.ZERO)>00)
                    .collect(Collectors.toMap(p -> ((List)p).get(1), p ->((List)p).get(2))));
            loadBidPrices();
        } finally {
            bidLock.unlock();
        }

        printOrderBook();
    }

    public Map<Object, Object> getAsks() {
        return Collections.unmodifiableMap(ask);
    }

    public Map<Object, Object> getBids() {
        return Collections.unmodifiableMap(bid);
    }

    private void loadAskPrices() {
        askLock.lock();
        try {
            reset(askPrices);
            int k = orderBookDepth-1;
            for(Object key : ask.descendingKeySet()){
                askPrices[k] = (String)key;
                k -= 1;
                if(k<0)
                    break;
            }
        } finally {
            askLock.unlock();
        }
    }

    private void loadBidPrices() {
        bidLock.lock();
        try {
            reset(bidPrices);
            int i = 0;
            for(Object key : bid.keySet()){
                bidPrices[i] = (String)key;
                i += 1;
                if(i == orderBookDepth) {
                    break;
                }
            }
        } finally {
            bidLock.unlock();
        }
    }

    private void printOrderBook() {
        logger.printLine("\n\n\n\n\n\n");
        logger.printLine("Current time is " + new Date() + "\n");
        logger.printLine("                  Price          Ask Size");
        logger.printLine("------------------------------------------");

        askLock.lock();
        try {
            for (String key : askPrices) {
                logger.printLine("                  " + key + "        " + ask.get(key));
            }
        } finally {
            askLock.unlock();
        }
        logger.printLine("------------------------------------------");

        bidLock.lock();
        try {
            for (String key : bidPrices) {
                logger.printLine(bid.get(key) + "        " + key);
            }
        } finally {
            bidLock.unlock();
        }

        logger.printLine("------------------------------------------");
        logger.printLine("Bid Size           Price\n");
    }

    private void reset(String[] data) {
        for(int i=0; i< data.length; i++) {
            data[i]=null;
        }
    }
}
