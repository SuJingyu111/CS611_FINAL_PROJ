package com.company.Bank;

import com.company.Account.Account;
import com.company.Account.AccountType;
import com.company.Persons.Customer;

import java.io.IOException;
import java.util.Map;

public class CustomerBalance {

    public static void run(Customer customer, Currency currency) throws IOException {

        System.out.println();
        System.out.println("********************************************************************************************");
        System.out.println("           Account Id      Balance         Account Type");
        int count = 1;
        //TODO: ADJUST FUNCTION CALL
        Map<AccountType, Account> accounts = customer.getAccounts();
        for(AccountType accountType : accounts.keySet()){
            System.out.println("<" + count + "> " + "           " +  accounts.get(accountType).getAccountId() + "           " + accounts.get(accountType).getBalance() + "           " + accountType);
            count += 1;

        }
        System.out.println("********************************************************************************************");
        System.out.println();
        CustomerOptions.options(customer, currency);


    }
}
