package com.coinbase.marketdata;

import com.coinbase.marketdata.log.LogWriter;
import com.coinbase.marketdata.log.SystemOutWriter;
import com.google.gson.Gson;

import java.net.URI;

public class MarketDataFeed {
    private static final Gson gson = new Gson();
    private static final LogWriter logger = new SystemOutWriter();
    private static final int DEFAULT_ORDER_BOOK_DEPTH = 10;

    public static void main(String[] args) {
        try {
            if(args.length != 1) {
                logger.printLine("Please supply the product id");
                return;
            }

            final WebsocketClientEndpoint clientEndPoint = new WebsocketClientEndpoint(new URI("wss://ws-feed.pro.coinbase.com"));
            final OrderBook orderBook = new OrderBook(DEFAULT_ORDER_BOOK_DEPTH);
            clientEndPoint.addMessageHandler(new WebsocketClientEndpoint.MessageHandler() {
                public void handleMessage(String message) {
                    logger.printLine(message);
                    L2ChannelData dataUpdate = gson.fromJson(message, L2ChannelData.class);
                    if("snapshot".equalsIgnoreCase(dataUpdate.getType())) {
                        orderBook.buildOrderBook(dataUpdate);
                    } else if("l2update".equalsIgnoreCase(dataUpdate.getType())) {
                        orderBook.updateOrderBook(dataUpdate);
                        sleep(); //Sleeping so that logs do not scroll fast
                    }
                }
            });
            clientEndPoint.sendMessage(getRequestJson(args[0]));

            while(true) {
                Thread.yield();
            }

        } catch (Exception ex) {
            logger.printLine(ex.getMessage());
        }
    }

    private static void sleep() {
        try {
            Thread.sleep(4000);
        } catch(Exception ex) {
            logger.printLine(ex.getMessage());
        }
    }

    private static String getRequestJson(String productId) {
        StringBuilder req = new StringBuilder();
        req.append("{\n");
        req.append("    \"type\": \"subscribe\",\n");
        req.append("    \"product_ids\": [\n");
        req.append("        \"").append(productId).append("\"\n");
        req.append("    ],\n");
        req.append("    \"channels\": [\n");
        req.append("        \"level2\"\n");
        req.append("    ]\n");
        req.append("}");
        return req.toString();
    }
}
