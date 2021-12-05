package com.company.Transactions;

import com.company.Account.AccountType;
import com.company.Persons.Person;

import java.util.Date;

public class Transaction {

    private String id;

    private Date timestamp;

    private double amount;

    private Person p;

    private AccountType accountType;

    public Transaction(String id, Date timestamp, double amount, Person p, AccountType accountType) {
        this.id = id;
        this.timestamp = timestamp;
        this.amount = amount;
        this.p = p;
        this.accountType = accountType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Person getP() {
        return p;
    }

    public void setP(Person p) {
        this.p = p;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getId()).append(",");
        stringBuilder.append(getTimestamp().toString()).append(",");
        stringBuilder.append(getAmount()).append(",");
        stringBuilder.append(getP().getName()).append(",");
        stringBuilder.append(getAccountType());
        return stringBuilder.toString();
    }
}
