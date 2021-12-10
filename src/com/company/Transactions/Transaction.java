package com.company.Transactions;

import com.company.Account.AccountType;
import com.company.Persons.Person;

import java.time.LocalDate;
import java.util.Date;

public class Transaction {

    private String id;

    private LocalDate timestamp;

    private double amount;

    private String personId;

    private TxnType type;

    public Transaction(String id, LocalDate timestamp, double amount, String personId, TxnType type) {
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

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
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
