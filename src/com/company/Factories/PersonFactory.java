package com.company.Factories;

import com.company.Account.Account;
import com.company.Account.AccountType;
import com.company.Persons.Customer;
import com.company.Persons.Manager;
import com.company.Exceptions.PersonNotFoundException;
import com.company.Bank.Parser;

import java.io.FileNotFoundException;
import java.util.*;

public class PersonFactory{

    private Parser parser;

    private AccountFactory accountFactory;

    public PersonFactory() {
        parser = new Parser();
        accountFactory = new AccountFactory();
    }

    public Customer produceCustomer(String name, String pwd) throws PersonNotFoundException {
        int id = parser.checkPresence(name, pwd, true);
        List<String[]> accountInfo = getAccountInfo(name, pwd, true);
        Map<AccountType, Account> accounts = accountFactory.produceAccountMap(accountInfo);
        return new Customer(id, name, pwd, accounts);
    }

    public Customer produceNewCustomer(int id, String name, String pwd) {
        return new Customer(id, name, pwd, new HashMap<>());
    }

    public Manager produceManager(String name, String pwd) {
        int id = parser.checkPresence(name, pwd, false);
        List<String[]> accountInfo = getAccountInfo(name, pwd, false);
        Map<AccountType, Account> accounts = accountFactory.produceAccountMap(accountInfo);
        return new Manager(id, name, pwd, accounts);
    }

    private List<String[]> getAccountInfo(String name, String pwd, boolean isCustomer) {
        List<String> accountIdList = parser.parsePersonAccountIds(name, pwd, isCustomer);
        List<String[]> accountInfo = new ArrayList<>();
        for (String id : accountIdList) {
            accountInfo.add(parser.parseAccount(id, isCustomer));
        }
        return accountInfo;
    }
}
