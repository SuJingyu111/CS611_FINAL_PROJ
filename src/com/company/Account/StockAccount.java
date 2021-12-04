package com.company.Account;

import com.company.Exceptions.InadequateBalanceException;
import com.company.Exceptions.NotEnoughShareException;
import com.company.Exceptions.StockNotExistException;
import com.company.Stock.Stock;
import com.company.Stock.StockMarket;

import java.util.HashMap;
import java.util.Map;

public class StockAccount extends Account{

    private Map<String, Stock> stockLookUpMap;

    private Map<Stock, Integer> sharesHolding;

    private StockMarket stockMarket;

    public StockAccount(String accountId, String ownerName, String pwd, AccountType type, int balance) {
        super(accountId, ownerName, pwd, type, balance);
        sharesHolding = new HashMap<>();
        stockMarket = StockMarket.getInstance();
    }

    public void buyShare(String corpName, int amount) throws StockNotExistException, InadequateBalanceException {
        Stock stock = stockMarket.getStockByName(corpName);
        double cost = stock.getPrice() * amount;
        double currentBalance = getBalance();
        if (currentBalance < cost) {
            throw new InadequateBalanceException();
        }
        stockLookUpMap.putIfAbsent(corpName, stock);
        sharesHolding.put(stock, sharesHolding.getOrDefault(stock, 0) + amount);
        setBalance(currentBalance - cost);
    }

    public void sellShare(String corpName, int amount)  throws StockNotExistException, NotEnoughShareException {
        if (!stockLookUpMap.containsKey(corpName)) {
            throw new StockNotExistException();
        }
        Stock stock = stockLookUpMap.get(corpName);
        int holdingAmount = sharesHolding.get(stock);
        if (holdingAmount < amount) {
            throw new NotEnoughShareException();
        }
        setBalance(getBalance() + amount * stock.getPrice());
        holdingAmount -= amount;
        if (holdingAmount == 0) {
            stockLookUpMap.remove(corpName);
            sharesHolding.remove(stock);
        }
        else {
            sharesHolding.put(stock, holdingAmount);
        }
    }

}
