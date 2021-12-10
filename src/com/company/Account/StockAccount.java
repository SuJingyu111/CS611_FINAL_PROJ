package com.company.Account;

import com.company.Bank.Currency;
import com.company.Bank.CurrencyType;
import com.company.Exceptions.InadequateBalanceException;
import com.company.Exceptions.NotEnoughShareException;
import com.company.Exceptions.StockNotExistException;
import com.company.Stock.Stock;
import com.company.Stock.StockMarket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class StockAccount extends Account{

    private Map<String, Stock> stockLookUpMap;

    private Map<Stock, Integer> sharesHolding;

    private StockMarket stockMarket;

    public StockAccount(String accountId, String ownerName, String pwd, AccountType type, Map<CurrencyType, Double> balance) {
        this(accountId, ownerName, pwd, type);
        setAllBalance(balance);
    }

    public StockAccount(String accountId, String ownerName, String pwd, AccountType type) {
        super(accountId, ownerName, pwd, type);
        sharesHolding = new HashMap<>();
        stockMarket = StockMarket.getInstance();
    }

    public void buyShare(String corpName, int amount, CurrencyType currencyType) throws StockNotExistException, InadequateBalanceException, IOException {
        Currency currency = new Currency();
        Stock stock = stockMarket.getStockByName(corpName);
        double cost = stock.getPrice() * amount;
        double currentBalance = getBalanceByCurrency(currencyType) * currency.getForex(currencyType.name());
        if (currentBalance < cost) {
            throw new InadequateBalanceException();
        }
        stockLookUpMap.putIfAbsent(corpName, stock);
        sharesHolding.put(stock, sharesHolding.getOrDefault(stock, 0) + amount);
        addToBalance(currencyType, -cost);
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
        addToBalance(CurrencyType.USD,getBalanceByCurrency(CurrencyType.USD) + amount * stock.getPrice());
        holdingAmount -= amount;
        if (holdingAmount == 0) {
            stockLookUpMap.remove(corpName);
            sharesHolding.remove(stock);
        }
        else {
            sharesHolding.put(stock, holdingAmount);
        }
    }

    //Should only use in account factory
    public void addShare(String corpName, int amount) {
        Stock stock = stockMarket.getStockByName(corpName);
        stockLookUpMap.put(corpName, stock);
        sharesHolding.put(stock, amount);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(super.toString());
        stringBuilder.append(",");
        for (Map.Entry<Stock, Integer> ent : sharesHolding.entrySet()) {
            stringBuilder.append(ent.getKey().getCorpName()).append(" ").append(ent.getValue()).append(" ");
        }
        stringBuilder.delete(stringBuilder.length() - (sharesHolding.isEmpty() ? 0 : 1), stringBuilder.length());
        return stringBuilder.toString();
    }

}
