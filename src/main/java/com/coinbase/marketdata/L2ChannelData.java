package com.coinbase.marketdata;


import java.util.List;

public class L2ChannelData {
    private String type;
    private String product_id;
    private List<List<String>> changes;
    private List<List<String>> asks;
    private List<List<String>> bids;

    public List<List<String>> getAsks() {
        return asks;
    }

    public void setAsks(List<List<String>> asks) {
        this.asks = asks;
    }

    public List<List<String>> getBids() {
        return bids;
    }

    public void setBids(List<List<String>> bids) {
        this.bids = bids;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public List<List<String>> getChanges() {
        return changes;
    }

    public void setChanges(List<List<String>> changes) {
        this.changes = changes;
    }
}
