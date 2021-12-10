package com.company.Bank;

import com.company.Account.Account;
import com.company.Account.AccountType;
import com.company.Persons.Customer;

import java.io.IOException;
import java.util.*;

public class CustomerBalance {

    public static void run(Customer customer, Currency currency) throws IOException {

        System.out.println();
        System.out.println("********************************************************************************************");
        System.out.println("           Account Id       Account Type   Currency    Balance ");
        int count = 1;
        Map<AccountType, List<Account>> accounts = customer.getAllAccounts();
        for(AccountType accountType : accounts.keySet()){
            List<Account> accountsByType = customer.getAccountsByType(accountType);
            for(Account account : accountsByType){
                System.out.println("<" + count + "> " + "           " +  account.getAccountId() + "           " + accountType);
                Map<CurrencyType, Double> map = account.getBalance();
                for (Map.Entry<CurrencyType, Double> entry : map.entrySet())
                    System.out.println("                    " + entry.getKey() + "        " + entry.getValue());
            }

            count += 1;

        }
        System.out.println("********************************************************************************************");
        System.out.println();
        CustomerOptions.options(customer, currency);


    }
}
