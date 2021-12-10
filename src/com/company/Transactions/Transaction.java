package com.company.Transactions;

import com.company.Account.AccountType;
import com.company.Persons.Person;

import java.util.Date;

public class Transaction {

    private String id;

    private Date timestamp;

    private double amount;

    private int personId;

    private TxnType type;

    public Transaction(String id, Date timestamp, double amount, int personId, TxnType type) {
        this.id = id;
        this.timestamp = timestamp;
        this.amount = amount;
        this.personId = personId;
        this.type = type;
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

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public TxnType getAccountType() {
        return type;
    }

    public void setAccountType(TxnType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getId()).append(",");
        stringBuilder.append(getTimestamp().toString()).append(",");
        stringBuilder.append(getAmount()).append(",");
        stringBuilder.append(getPersonId()).append(",");
        stringBuilder.append(getAccountType());
        return stringBuilder.toString();
    }

    public TxnType getType() {
        return type;
    }

    public void setType(TxnType type) {
        this.type = type;
    }
}
