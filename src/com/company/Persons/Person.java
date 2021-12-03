package com.company.Persons;

import com.company.Account.Account;
import com.company.Account.AccountType;
import com.company.Exceptions.AccountAlreadyExistException;
import com.company.Exceptions.AccountNotExistException;

import java.util.Map;

public class Person {

    private String name;

    private String pwd;

    private Map<AccountType, Account> accounts;

    public Person(String name, String pwd, Map<AccountType, Account> accounts) {
        this.name = name;
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public boolean isCorrectPwd(String inputPwd) {
        return pwd.equals(inputPwd);
    }

    public Account getAccount(AccountType type) throws AccountNotExistException {
        Account account = accounts.getOrDefault(type, null);
        if (account == null) {
            throw new AccountNotExistException();
        }
        return account;
    }

    public Map<AccountType, Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Map<AccountType, Account> accounts) {
        this.accounts = accounts;
    }

    public void addAccount(Account account) throws AccountAlreadyExistException {
        if (accounts.containsKey(account.getTYPE())) {
            throw new AccountAlreadyExistException();
        }
        accounts.put(account.getTYPE(), account);
    }
    //TODO
}
