package com.coinbase.marketdata;

import java.math.BigDecimal;
import java.util.Comparator;

public class AskComparator implements Comparator<String> {
    public int compare(String a, String b)
    {
        BigDecimal ba = new BigDecimal(a);
        BigDecimal bb = new BigDecimal(b);
        return bb.compareTo(ba);
    }
}

