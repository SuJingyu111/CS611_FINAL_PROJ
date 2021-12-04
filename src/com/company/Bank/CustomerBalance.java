package com.company.Bank;

import com.company.Account.Account;
import com.company.Account.AccountType;
import com.company.Persons.Customer;

import java.io.IOException;
import java.util.Map;

public class CustomerBalance {

    public static void run(Customer customer) throws IOException {

        System.out.println();
        System.out.println("********************************************************************************************");
        System.out.println("    Account Type      Balance");
        int count = 1;
        Map<AccountType, Account> accounts = customer.getAccounts();
        for(AccountType accountType : accounts.keySet()){
            System.out.println("<" + count + "> " + accountType + "           " + accounts.get(accountType).getBalance());
            count += 1;

        }
        System.out.println("********************************************************************************************");
        System.out.println();
        CustomerOptions.options(customer);

    }
}
