package com.coinbase.marketdata.log;

public class SystemOutWriter implements LogWriter{
    @Override
    public void printLine(String message) {
        System.out.println(message);
    }

    @Override
    public void print(String message) {
        System.out.print(message);
    }
}
