package com.company.Account;

public class Account {

    private String ownerName;

    private String accountId;

    private String pwd;

    private double balance;

    private AccountType TYPE;

    public Account(String accountId, String ownerName, String pwd, AccountType type, double balance) {
        this.ownerName = ownerName;
        this.accountId = accountId;
        this.pwd = pwd;
        this.balance = balance;
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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public AccountType getTYPE() {
        return TYPE;
    }

    public void setTYPE(AccountType TYPE) {
        this.TYPE = TYPE;
    }

    @Override
    public String toString() {
        return getAccountId() + "," +
                getOwnerName() + "," +
                getPwd() + "," +
                getTYPE() + "," +
                getBalance();
    }
}
