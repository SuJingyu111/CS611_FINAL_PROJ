package ATM.Persons;

import ATM.Account.Account;
import ATM.Account.AccountType;

import java.util.Map;

public class Manager extends Person{

    public Manager(String name, String pwd, Map<AccountType, Account> accounts) {
        super(name, pwd, accounts);
    }

}
