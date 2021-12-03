package ATM.Persons;

import ATM.Account.Account;
import ATM.Account.AccountType;
import ATM.Exceptions.AccountAlreadyExistException;
import ATM.Exceptions.AccountNotExistException;

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
