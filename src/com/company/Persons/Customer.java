package com.company.Persons;

import com.company.Account.Account;
import com.company.Account.AccountType;

import java.util.List;
import java.util.Map;

public class Customer extends Person{

    public Customer(int id, String name, String pwd, Map<AccountType, List<Account>> accounts) {
        super(id, name, pwd, accounts);
    }

}
