package com.company.Persons;

import com.company.Account.Account;
import com.company.Account.AccountType;

import java.util.Map;

public class Manager extends Person{

    public Manager(int id, String name, String pwd, Map<AccountType, Account> accounts) {
        super(id, name, pwd, accounts);
    }

}
