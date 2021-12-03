package com.company.Factories;

import com.company.Bank.Parser;
import com.company.Stock.Stock;

import java.util.HashMap;
import java.util.Map;

public class StockFactory {

    private Parser parser;

    public StockFactory() {
        parser = new Parser();
    }

    public Stock produceSingleStock(String corpName, double price) {
        return new Stock(corpName, price);
    }

    public Map<String, Stock> getAllStocks() {
        Map<String, Double> stockInfo = parser.parseAllStockInfo();
        Map<String, Stock> allStocks = new HashMap<>();
        for (Map.Entry<String, Double> info : stockInfo.entrySet()) {
            allStocks.put(info.getKey(), produceSingleStock(info.getKey(), info.getValue()));
        }
        return allStocks;
    }

}
