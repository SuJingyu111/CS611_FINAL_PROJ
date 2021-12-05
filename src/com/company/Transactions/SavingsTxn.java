package com.company.Transactions;

import com.company.Account.AccountType;
import com.company.Persons.Person;

import java.util.Date;

public class SavingsTxn extends Transaction{

    public SavingsTxn(String id, Date timestamp, double amount, Person p, AccountType accountType) {
        super(id, timestamp, amount, p, accountType);
    }

}
