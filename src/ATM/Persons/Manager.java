package ATM.Persons;

import ATM.Account.Account;
import ATM.Account.AccountType;

import java.util.Map;

public class Manager extends Person{

    public Manager(int id, String name, String pwd, Map<AccountType, Account> accounts) {
        super(id, name, pwd, accounts);
    }

}
