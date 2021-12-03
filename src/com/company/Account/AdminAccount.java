package com.company.Account;

public class AdminAccount extends Account{

    public AdminAccount(String accountId, String ownerName, String pwd, AccountType type, double balance) {
        super(accountId, ownerName, pwd, type, balance);
    }

}
