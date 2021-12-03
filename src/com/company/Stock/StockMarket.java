package com.company.Stock;

import com.company.Exceptions.StockNotExistException;
import com.company.Factories.StockFactory;

import java.util.Map;

public class StockMarket {

    private Map<String, Stock> stockInfo;

    private StockFactory stockFactory;

    private static StockMarket stockMarket = new StockMarket();

    private StockMarket() {
        stockFactory = new StockFactory();
        stockInfo = stockFactory.getAllStocks();
    }

    public static StockMarket getInstance() {
        return stockMarket;
    }

    public Stock getStockByName(String name) throws StockNotExistException {
        if (!stockInfo.containsKey(name)) {
            throw new StockNotExistException();
        }
        return stockInfo.get(name);
    }
}
