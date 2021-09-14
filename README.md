# GSR
The project connects to Coinbase Websocket server, subscribes to Level2 data for a product and updates the OrderBook.

A delay of 4sec is introduced after each L2Update message so that the logs can be seen on the console without quickly scrolling up.

## Sample command:
### java com.coinbase.marketdata.MarketDataFeed ETH-USD

## Sample result:

Current time is Tue Sep 14 16:52:06 EDT 2021

|                  Price      |    Ask Size
------------------------------------------
|                  3378.70    |    0.03431310
|                  3378.69    |    3.58017332
|                  3378.68    |    0.93602875
|                  3378.52    |    2.36800000
|                  3378.34    |    0.03845904
|                  3378.23    |    0.83000000
|                  3378.22    |    0.21113752
|                  3377.99    |    0.03835845
|                  3377.70    |    0.40344572
|                  3377.69    |    0.60344572
------------------------------------------
| 0.21113752    |    3377.54
| 1.00000000    |    3377.52
| 3.00000000    |    3377.51
| 2.36800000    |    3377.49
| 15.86712999   |     3377.30
| 2.36800000    |    3377.22
| 0.03390483    |    3377.09
| 1.31185866    |    3377.07
| 29.36316722   |     3377.04
| 19.04431234   |     3376.96
------------------------------------------
| Bid Size       |    Price
