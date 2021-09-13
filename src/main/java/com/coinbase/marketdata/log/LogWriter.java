package com.coinbase.marketdata.log;

public interface LogWriter {
    void printLine(String message);
    void print(String message);
}
