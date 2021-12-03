package com.company.Persons;

import com.company.Account.Account;
import com.company.Account.AccountType;

import java.util.Map;

public class Manager extends Person{

    public Manager(String name, String pwd, Map<AccountType, Account> accounts) {
        super(name, pwd, accounts);
    }

}
