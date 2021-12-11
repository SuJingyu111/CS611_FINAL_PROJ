package com.company.Bank;

import com.company.Account.Account;
import com.company.Account.AccountType;
import com.company.Exceptions.AccountNotExistException;
import com.company.Factories.AccountFactory;
import com.company.Persons.Customer;
import com.company.Stock.StockMarket;
import com.company.Utils.Writer;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static com.company.Account.AccountType.*;
import static com.company.Bank.CurrencyType.USD;

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

            customer.deleteAccount(SAVINGS, ID);
            try{
                AllSavingsAccounts = customer.getAccountsByType(SAVINGS);
            }
            catch(AccountNotExistException e){
                writer.deleteAccount(SAVINGS, ID, true);
            }

            if(!AllSavingsAccounts.isEmpty()){writer.deleteAccount(SAVINGS, ID, false);}
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

            customer.deleteAccount(CHECKINGS, ID);
            try{
                AllCheckingsAccounts = customer.getAccountsByType(CHECKINGS);
            }
            catch(AccountNotExistException e){
                writer.deleteAccount(CHECKINGS, ID, true);
            }

            if(!AllCheckingsAccounts.isEmpty()){writer.deleteAccount(CHECKINGS, ID, false);}
        }

        else if(choice.equals("3")){
            List<Account> AllStockAccounts = customer.getAccountsByType(STOCK);
            Account stockAccount = AllStockAccounts.get(0);
            String ID = stockAccount.getAccountId();

            customer.deleteAccount(STOCK, ID);
            writer.deleteAccount(STOCK, ID, true);

        }

        feeToBank(customer);
        CustomerBalance.run(customer, currency, stockMarket);
    }



    public static void feeToBank(Customer customer) throws IOException {

        Writer writer = new Writer();

        AccountFactory accountFactory = new AccountFactory();
        List<Account> allAdminAccounts = accountFactory.produceAccountsByType(ADMIN);
        Account adminAccount = allAdminAccounts.get(0);
        adminAccount.addToBalance(USD, 5.00);

        List<Account> allAccounts;

        try{
            allAccounts = customer.getAccountsByType(CHECKINGS);
        }
        catch(AccountNotExistException e){
            allAccounts = customer.getAccountsByType(SAVINGS);
        }

        for(Account account : allAccounts){
            Map<CurrencyType, Double> map = account.getBalance();
            double value = map.get(USD);
            if(value > 5.0){
                map.put(USD, value - 5.0);
                account.setAllBalance(map);
                writer.updateAccountToDisk(account);
                break;
            }
        }
        writer.updateAccountToDisk(adminAccount);
    }
}
