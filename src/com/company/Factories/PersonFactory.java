package com.company.Factories;

import com.company.Account.Account;
import com.company.Account.AccountType;
import com.company.Persons.Customer;
import com.company.Persons.Manager;
import com.company.Exceptions.PersonNotFoundException;
import com.company.Utils.Parser;

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
        Map<AccountType, List<String[]>> accountInfo = getAccountInfo(name, pwd, true);
        Map<AccountType, List<Account>> accounts = accountFactory.produceAccountMap(accountInfo);
        return new Customer(String.valueOf(id), name, pwd, accounts);
    }

    public Customer produceNewCustomer(String id, String name, String pwd) {
        return new Customer(id, name, pwd, new HashMap<>());
    }

    public Manager produceManager(String name, String pwd) {
        int id = parser.checkPresence(name, pwd, false);
        Map<AccountType, List<String[]>> accountInfo = getAccountInfo(name, pwd, false);
        Map<AccountType, List<Account>> accounts = accountFactory.produceAccountMap(accountInfo);
        return new Manager(String.valueOf(id), name, pwd, accounts);
    }

    public Manager produceNewManager(String id, String name, String pwd) {
        return new Manager(id, name, pwd, new HashMap<>());
    }

    private Map<AccountType, List<String[]>> getAccountInfo(String name, String pwd, boolean isCustomer) {
        //int personId = parser.checkPresence(name, pwd, isCustomer);
        Map<AccountType, Boolean> accountExistenceMap = parser.parsePersonAccountExistence(name, pwd, isCustomer);
        Map<AccountType, List<String[]>> accountInfo = new HashMap<>();
        for (AccountType type : AccountType.values()) {
            if (accountExistenceMap.get(type)) {
                accountInfo.put(type, parser.parseAccounts(type, name, pwd));
            }
        }
        return accountInfo;
    }
}
