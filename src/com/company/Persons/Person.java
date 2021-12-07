package com.company.Persons;

import com.company.Account.*;
import com.company.Exceptions.AccountAlreadyExistException;
import com.company.Exceptions.AccountNotExistException;

import java.util.Map;

public class Person {

    private int id;

    private String name;

    private String pwd;

    private Map<AccountType, Account> accounts;

    public Person(int id, String name, String pwd, Map<AccountType, Account> accounts) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
        this.accounts = accounts;
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

        accounts.put(account.getTYPE(), account);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(id).append(",");
        stringBuilder.append(name).append(",");
        stringBuilder.append(pwd).append(",");
        appendAccId(AccountType.CHECKINGS, stringBuilder);
        appendAccId(AccountType.SAVINGS, stringBuilder);
        appendAccId(AccountType.LOAN, stringBuilder);
        appendAccId(AccountType.STOCK, stringBuilder);
        appendAccId(AccountType.ADMIN, stringBuilder);
        stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
        return stringBuilder.toString();
    }

    private void appendAccId(AccountType type, StringBuilder stringBuilder) {
        Account account = accounts.get(type);
        stringBuilder.append(account == null ? "" : account.getAccountId()).append(",");
    }
}
