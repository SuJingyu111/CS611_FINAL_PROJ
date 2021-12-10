package com.company.Account;

import com.company.Bank.CurrencyType;

import java.util.Map;

public class LoanAccount extends Account{

    public LoanAccount(String accountId, String ownerName, String pwd, AccountType type) {
        super(accountId, ownerName, pwd, type);
    }

    public LoanAccount(String accountId, String ownerName, String pwd, AccountType type, Map<CurrencyType, Double> balance) {
        this(accountId, ownerName, pwd, type);
        setAllBalance(balance);
    }

}
