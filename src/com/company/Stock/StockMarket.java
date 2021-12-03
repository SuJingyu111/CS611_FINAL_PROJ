package com.company.Stock;

import com.company.Factories.StockFactory;

import java.util.Map;

public class StockMarket {

    private Map<String, Stock> stockInfo;

    private StockFactory stockFactory;

    public StockMarket() {
        stockFactory = new StockFactory();
        stockInfo = stockFactory.getAllStocks();
    }

    public Stock getStockByName(String name) {
        return stockInfo.get(name);
    }
}
