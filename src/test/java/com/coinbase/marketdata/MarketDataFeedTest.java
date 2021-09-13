package com.coinbase.marketdata;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MarketDataFeedTest {
    OrderBook o;

    @Before
    public void setUp()
    {
        o = new OrderBook(3);
        L2ChannelData data = new L2ChannelData();
        data.setType("snapshot");
        data.setProduct_id("ETH-USD");
        List asks = new ArrayList();
        List<String> ask1 = new ArrayList<>();
        ask1.add("3388.43");
        ask1.add("1.61700000");
        asks.add(ask1);
        List<String> ask2 = new ArrayList<>();
        ask2.add("3388.55");
        ask2.add("1.50000000");
        asks.add(ask2);
        List<String> ask3 = new ArrayList<>();
        ask3.add("3388.64");
        ask3.add("0.03164827");
        asks.add(ask3);
        data.setAsks(asks);


        List bids = new ArrayList();
        List<String> bid1 = new ArrayList<>();
        bid1.add("3388.42");
        bid1.add("0.58386757");
        bids.add(bid1);
        List<String> bid2 = new ArrayList<>();
        bid2.add("3388.41");
        bid2.add("0.58899451");
        bids.add(bid2);
        List<String> bid3 = new ArrayList<>();
        bid3.add("3387.87");
        bid3.add("1.58115185");
        bids.add(bid3);
        data.setBids(bids);

        o.buildOrderBook(data);
    }

    @Test
    public void testAskMap() {
        Map<Object, Object> askMap = o.getAsks();
        Assert.assertEquals(3, askMap.size());
        Assert.assertEquals("0.03164827", askMap.get("3388.64"));
    }

    @Test
    public void testBidMap() {
        Map<Object, Object> bidMap = o.getBids();
        Assert.assertEquals(3, bidMap.size());
        Assert.assertEquals("1.58115185", bidMap.get("3387.87"));
    }
}
