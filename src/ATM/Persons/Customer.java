package ATM.Persons;

import ATM.Account.Account;
import ATM.Account.AccountType;
import ATM.Exceptions.AccountNotExistException;

import java.util.Map;

public class Customer extends Person{

    public Customer(int id, String name, String pwd, Map<AccountType, Account> accounts) {
        super(id, name, pwd, accounts);
    }

}
