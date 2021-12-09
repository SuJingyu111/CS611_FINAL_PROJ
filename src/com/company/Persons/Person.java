package com.company.Persons;

import com.company.Account.*;
import com.company.Exceptions.AccountAlreadyExistException;
import com.company.Exceptions.AccountNotExistException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Person {

    private int id;

    private String name;

    private String pwd;

    private Map<AccountType, List<Account>> accounts;

    public Person(int id, String name, String pwd, Map<AccountType, List<Account>> accounts) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
        this.accounts = accounts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Account getAccountById(AccountType type, String accountId) throws AccountNotExistException {
        List<Account> accountList = getAccountsByType(type);
        for (Account account : accountList) {
            if (account.getAccountId().equals(accountId)) {
                return account;
            }
        }
        throw new AccountNotExistException();
    }

    public List<Account> getAccountsByType(AccountType type) throws AccountNotExistException {
        List<Account> accountList = accounts.getOrDefault(type, new ArrayList<>());
        if (accountList.size() == 0) {
            throw new AccountNotExistException();
        }
        return accountList;
    }

    public Map<AccountType, List<Account>> getAllAccounts() {
        return accounts;
    }

    public void setAccounts(Map<AccountType, List<Account>> accounts) {
        this.accounts = accounts;
    }

    public void addAccount(Account account) throws AccountAlreadyExistException {
        List<Account> accountList = accounts.getOrDefault(account.getTYPE(), new ArrayList<>());
        accountList.add(account);
        accounts.put(account.getTYPE(), accountList);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(id).append(",");
        stringBuilder.append(name).append(",");
        stringBuilder.append(pwd);
        //appendAccId(AccountType.CHECKINGS, stringBuilder);
        //appendAccId(AccountType.SAVINGS, stringBuilder);
        //appendAccId(AccountType.LOAN, stringBuilder);
        //appendAccId(AccountType.STOCK, stringBuilder);
        //appendAccId(AccountType.ADMIN, stringBuilder);
        //stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
        return stringBuilder.toString();
    }

    //private void appendAccId(AccountType type, StringBuilder stringBuilder) {
    //    Account account = accounts.get(type);
    //    stringBuilder.append(account == null ? "" : account.getAccountId()).append(",");
    //}
}
