package com.company.Transactions;

import com.company.Persons.Customer;

import java.time.LocalDate;


public class AdminTxn extends Transaction{

    //Which customer this transaction is related to, null if none related
    private final Customer customer;

    //For AdminTxn, personId is the id of the customer, not manager, as all managers should be able to view the Txn
    public AdminTxn(String id, LocalDate timestamp, double amount, String personId, Customer customer) {
        super(id, timestamp, amount, personId, TxnType.ADMIN);
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }
}