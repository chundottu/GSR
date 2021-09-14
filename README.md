# GSR
The project connects to Coinbase Websocket server, subscribes to Level2 data for a product and updates the OrderBook.

A delay of 4sec is introduced after each L2Update message so that the logs can be seen on the console without quickly scrolling up.

## Sample command:
### java com.coinbase.marketdata.MarketDataFeed ETH-USD

