package ATM.Factories;

import ATM.Account.Account;
import ATM.Account.AccountType;
import ATM.Persons.Customer;
import ATM.Persons.Manager;
import ATM.Exceptions.PersonNotFoundException;
import com.company.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class PersonFactory{

    private Parser parser;

    private AccountFactory accountFactory;

    public PersonFactory() {
        parser = new Parser();
        accountFactory = new AccountFactory();
    }

    public Customer produceCustomer(String name, String pwd) throws FileNotFoundException, PersonNotFoundException {
        List<String[]> accountInfo = getAccountInfo(name, pwd, true);
        Map<AccountType, Account> accounts = accountFactory.produceAccountMap(accountInfo);
        return new Customer(name, pwd, accounts);
    }

    public Manager produceManager(String name, String pwd) {
        List<String[]> accountInfo = getAccountInfo(name, pwd, false);
        Map<AccountType, Account> accounts = accountFactory.produceAccountMap(accountInfo);
        return new Manager(name, pwd, accounts);
    }

    private List<String[]> getAccountInfo(String name, String pwd, boolean isCustomer) {
        List<String> accountIdList = parser.parsePersonAccountIds(name, pwd, isCustomer);
        List<String[]> accountInfo = new ArrayList<>();
        for (String id : accountIdList) {
            accountInfo.add(parser.parseAccount(id, isCustomer));
        }
        return accountInfo;
    }
}
