package com.company.Account;

import com.company.Bank.CurrencyType;

import java.util.HashMap;
import java.util.Map;

public class Account {

    private String ownerName;

    private String accountId;

    private String pwd;

    private Map<CurrencyType, Double> balance;

    private AccountType TYPE;

    public Account(String accountId, String ownerName, String pwd, AccountType type) {
        this.ownerName = ownerName;
        this.accountId = accountId;
        this.pwd = pwd;
        this.TYPE = type;
        this.balance = new HashMap<>();
    }

    public Account(String accountId, String ownerName, String pwd, AccountType type, CurrencyType currency, double balance) {
        this.ownerName = ownerName;
        this.accountId = accountId;
        this.pwd = pwd;
        this.balance.put(currency, balance);
        this.TYPE = type;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Map<CurrencyType, Double> getBalance() {
        return balance;
    }

    public double getBalanceByCurrency(CurrencyType currencies) {
        return balance.get(currencies);
    }

    public void setBalance(CurrencyType currencyType, double balance) {
        this.balance.put(currencyType, balance);
    }

    public void setAllBalance(Map<CurrencyType, Double> balance) {
        this.balance = balance;
    }

    public void addToBalance(CurrencyType currencyType, double balance) {
        this.balance.put(currencyType, this.balance.getOrDefault(currencyType, 0.0) + balance);
    }

    public AccountType getTYPE() {
        return TYPE;
    }

    public void setTYPE(AccountType TYPE) {
        this.TYPE = TYPE;
    }

    //TODO: MODIFY THIS
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(getAccountId() + "," +
                getOwnerName() + "," +
                getPwd() + "," +
                getTYPE() + ",");
        for (Map.Entry<CurrencyType, Double> ent : balance.entrySet()) {
            sb.append(ent.getKey().name()).append(" ").append(ent.getKey()).append(" ");
        }
        sb.delete(sb.length() - (balance.isEmpty() ? 1 : 2), sb.length());
        return sb.toString();
    }
}
