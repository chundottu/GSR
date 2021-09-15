# GSR
The project connects to Coinbase Websocket server, subscribes to Level2 data for a product and updates the OrderBook.

A delay of 4sec is introduced after each L2Update message so that the logs can be seen on the console without quickly scrolling up.

## Sample command:
### java com.coinbase.marketdata.MarketDataFeed ETH-USD

## Sample result:

<pre>
Current time is Wed Sep 15 12:51:53 EDT 2021

                  Price          Ask Size
------------------------------------------
                  3518.16        4.26482214
                  3517.99        2.27422800
                  3517.96        0.03614393
                  3517.87        0.50000000
                  3517.55        2.27464000
                  3517.50        0.46710341
                  3517.40        1.65500000
                  3517.24        0.18651036
                  3517.15        0.18827404
                  3517.14        6.17002911
------------------------------------------
3.41306636        3517.13
2.27449600        3517.07
1.42939680        3516.94
2.30897325        3516.89
0.43977019        3516.72
2.27449600        3516.71
8.02646251        3516.69
1.00000000        3516.57
0.26508000        3516.56
0.14216776        3516.55
------------------------------------------
Bid Size           Price
</pre>
