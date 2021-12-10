package com.company.Transactions;

import java.util.Date;
import java.util.Map;

public class StockTxn extends Transaction{

    private Map<String, Integer> stockAmountMap;

    //Amount is positive if buy,negative if sell
    public StockTxn(String id, Date timestamp, double amount, int personId, Map<String, Integer> purchaseInfo) {
        super(id, timestamp, amount, personId, TxnType.STOCK);
        setStockAmountMap(purchaseInfo);
    }

    public Map<String, Integer> getStockAmountMap() {
        return stockAmountMap;
    }

    public void setStockAmountMap(Map<String, Integer> stockAmountMap) {
        this.stockAmountMap = stockAmountMap;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(super.toString());
        for (Map.Entry<String, Integer> record : stockAmountMap.entrySet()) {
            stringBuilder.append(record.getKey()).append(",").append(record.getValue()).append(",");
        }
        stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
        return stringBuilder.toString();
    }
}
