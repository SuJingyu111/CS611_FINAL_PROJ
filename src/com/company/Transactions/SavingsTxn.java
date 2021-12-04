package com.company.Transactions;

import com.company.Account.AccountType;
import com.company.Persons.Person;

import java.util.Date;

public class SavingsTxn extends Transaction{

    public SavingsTxn(String id, Date timestamp, double amount, Person p, AccountType accountType) {
        super(id, timestamp, amount, p, accountType);
    }

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
