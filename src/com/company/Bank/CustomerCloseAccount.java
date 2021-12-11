package com.company.Bank;

import com.company.Account.Account;
import com.company.Account.AccountType;
import com.company.Persons.Customer;
import com.company.Stock.StockMarket;
import com.company.Utils.Writer;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static com.company.Account.AccountType.*;

public class CustomerCloseAccount {

    public static void run(Customer customer, Currency currency, StockMarket stockMarket) throws IOException {

        Scanner input = new Scanner(System.in);
        Writer writer = new Writer();

        System.out.println();
        System.out.println("********************************************************************************************");
        System.out.println("           Account Id       Account Type   Currency    Balance ");
        int count = 1;
        Map<AccountType, List<Account>> accounts = customer.getAllAccounts();
        for(AccountType accountType : accounts.keySet()){
            List<Account> accountsByType = customer.getAccountsByType(accountType);
            for(Account account : accountsByType){
                System.out.println("<" + count + "> " + "           " +  account.getAccountId() + "             " + accountType);
                Map<CurrencyType, Double> map = account.getBalance();
                for (Map.Entry<CurrencyType, Double> entry : map.entrySet()){
                    System.out.println("                                             " + entry.getKey() + "         " + entry.getValue());
                }
                count += 1;
            }
        }
        System.out.println("********************************************************************************************");
        System.out.println();


        System.out.println("Enter type of account to be closed : ");
        System.out.println("<1> Savings");
        System.out.println("<2> Checkings");
        System.out.println("<3> Stocks");
        String choice = input.next();

        while(!choice.equals("1") && !choice.equals("2") && !choice.equals("3")){
            System.out.println("You've entered an incorrect input !");
            System.out.println("<1> Savings");
            System.out.println("<2> Checkings");
            System.out.println("<3> Stocks");

            choice = input.next();
        }

        if(choice.equals("1")){

            List<Account> AllSavingsAccounts = customer.getAccountsByType(SAVINGS);
            System.out.println();
            System.out.println("********************************************************************************************");
            System.out.println("           Account Id      Currency      Balance");
            count = 1;
            for(Account account : AllSavingsAccounts){
                System.out.println("<" + count + "> " + "           " +  account.getAccountId());
                Map<CurrencyType, Double> map = account.getBalance();
                for (Map.Entry<CurrencyType, Double> entry : map.entrySet()){
                    System.out.println("                    " + entry.getKey() + "        " + entry.getValue());
                }
                count += 1;
            }
            System.out.println("********************************************************************************************");

            System.out.println();
            System.out.println("Enter the account ID :  ");
            String ID = input.next();

            writer.deleteAccount(SAVINGS, ID);

        }

        else if(choice.equals("2")){

            List<Account> AllCheckingsAccounts = customer.getAccountsByType(CHECKINGS);
            System.out.println();
            System.out.println("********************************************************************************************");
            System.out.println("           Account Id      Currency      Balance");
            count = 1;
            for(Account account : AllCheckingsAccounts){
                System.out.println("<" + count + "> " + "           " +  account.getAccountId());
                Map<CurrencyType, Double> map = account.getBalance();
                for (Map.Entry<CurrencyType, Double> entry : map.entrySet()){
                    System.out.println("                    " + entry.getKey() + "        " + entry.getValue());
                }
                count += 1;
            }
            System.out.println("********************************************************************************************");

            System.out.println();
            System.out.println("Enter the account ID :  ");
            String ID = input.next();

            writer.deleteAccount(CHECKINGS, ID);
        }

        else if(choice.equals("3")){
            List<Account> AllStockAccounts = customer.getAccountsByType(STOCK);
            Account stockAccount = AllStockAccounts.get(0);
            String ID = stockAccount.getAccountId();

            writer.deleteAccount(STOCK, ID);

        }

        CustomerBalance.run(customer, currency, stockMarket);
    }
}
