package com.company.Bank.CustomerAllOptions;

import com.company.Account.Account;
import com.company.Currency.Currency;
import com.company.Currency.CurrencyType;
import com.company.Factories.AccountFactory;
import com.company.Persons.Customer;
import com.company.Stock.StockMarket;
import com.company.Transactions.Transaction;
import com.company.Transactions.TransferTxn;
import com.company.Utils.Writer;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import static com.company.Account.AccountType.*;

public class CustomerTransfer {

    public static void run(Customer customer, Currency currency, StockMarket stockMarket) throws IOException {

        Scanner input = new Scanner(System.in);
        Writer writer = new Writer();

        System.out.println();
        System.out.println("Enter the account you want to transfer from : ");
        System.out.println("<1> Checkings");
        System.out.println("<2> Savings");
        String choice1 = input.next();

        while(!choice1.equals("1") && !choice1.equals("2")){
            System.out.println();
            System.out.println("You've entered the wrong input !");
            System.out.println("Enter the account you want to transfer from : ");
            System.out.println("<1> Checkings");
            System.out.println("<2> Savings");
            choice1 = input.next();

        }

        System.out.println();
        System.out.println("Please enter the currency you want to transfer : ");
        System.out.println("<1> USD");
        System.out.println("<2> EUR");
        System.out.println("<3> CAD");
        System.out.println("<4> JPY");
        String choice2 = input.next();

        if(!choice2.equals("1") && !choice2.equals("2") && !choice2.equals("3") && !choice2.equals("4")){

            System.out.println("You've entered the wrong input ! ");
            System.out.println();
            System.out.println("Please enter the currency you want to withdraw : ");
            System.out.println("<1> USD");
            System.out.println("<2> EUR");
            System.out.println("<3> CAD");
            System.out.println("<4> JPY");
            choice2 = input.next();
        }

        System.out.println();
        System.out.println("Enter the amount : ");
        double value  = input.nextDouble();

        if(choice1.equals("1")){

            List<Account> AllCheckingsAccounts = customer.getAccountsByType(CHECKINGS);
            System.out.println();
            System.out.println("********************************************************************************************");
            System.out.println("           Account Id      Currency      Balance");
            int count = 1;
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
            System.out.println("Enter the account ID to be transferred from :  ");
            String ID1 = input.next();

            for(Account account : AllCheckingsAccounts){
                if(account.getAccountId().equals(ID1)){
                    if(choice2.equals("1")){ account.addToBalance(CurrencyType.USD, -value);}
                    else if(choice2.equals("2")){account.addToBalance(CurrencyType.EUR, -value);}
                    else if(choice2.equals("3")){account.addToBalance(CurrencyType.CAD, -value);}
                    else if(choice2.equals("4")){account.addToBalance(CurrencyType.JPY, -value);}
                    writer.updateAccountToDisk(account);
                }
            }

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
            System.out.println("Enter the account ID to be transferred to :  ");
            String ID2 = input.next();

            for(Account account : AllSavingsAccounts){
                if(account.getAccountId().equals(ID2)){
                    if(choice2.equals("1")){ account.addToBalance(CurrencyType.USD, value);}
                    else if(choice2.equals("2")){account.addToBalance(CurrencyType.EUR, value);}
                    else if(choice2.equals("3")){account.addToBalance(CurrencyType.CAD, value);}
                    else if(choice2.equals("4")){account.addToBalance(CurrencyType.JPY, value);}
                    writer.updateAccountToDisk(account);
                }
            }

            writer.writeTxn(recordTransaction(value, customer.getId(), ID1, ID2));
        }

        else if(choice1.equals("2")) {

            List<Account> AllSavingsAccounts = customer.getAccountsByType(SAVINGS);
            System.out.println();
            System.out.println("********************************************************************************************");
            System.out.println("           Account Id      Currency      Balance");
            int count = 1;
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
            System.out.println("Enter the account ID to be transferred from :  ");
            String ID1 = input.next();

            for (Account account : AllSavingsAccounts) {
                if (account.getAccountId().equals(ID1)) {
                    if(choice2.equals("1")){ account.addToBalance(CurrencyType.USD, -(value + 5.0)); feeToBank(CurrencyType.USD);}
                    else if(choice2.equals("2")){account.addToBalance(CurrencyType.EUR, -(value + 5.0)); feeToBank(CurrencyType.EUR);}
                    else if(choice2.equals("3")){account.addToBalance(CurrencyType.CAD, -(value + 5.0)); feeToBank(CurrencyType.CAD);}
                    else if(choice2.equals("4")){account.addToBalance(CurrencyType.JPY, -(value + 5.0)); feeToBank(CurrencyType.JPY);}
                    writer.updateAccountToDisk(account);
                }
            }

            System.out.println();
            System.out.println("Enter the account to be transferred to :");
            System.out.println("<1> Checkings");
            System.out.println("<2> Stock");
            String choice3 = input.next();

            while(!choice3.equals("1") && !choice3.equals("2")){
                System.out.println();
                System.out.println("You've entered an incorrect input !");
                System.out.println("Enter the account to be transferred to :");
                System.out.println("<1> Checkings");
                System.out.println("<2> Stock");
                choice3 = input.next();
            }

            if(choice3.equals("1")){

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
                System.out.println("Enter the account ID to be transferred to :  ");
                String ID2 = input.next();

                for (Account account : AllCheckingsAccounts) {
                    if (account.getAccountId().equals(ID2)) {
                        if(choice2.equals("1")){ account.addToBalance(CurrencyType.USD, value);}
                        else if(choice2.equals("2")){account.addToBalance(CurrencyType.EUR, value);}
                        else if(choice2.equals("3")){account.addToBalance(CurrencyType.CAD, value);}
                        else if(choice2.equals("4")){account.addToBalance(CurrencyType.JPY, value);}
                        writer.updateAccountToDisk(account);
                    }
                }
                writer.writeTxn(recordTransaction(value, customer.getId(), ID1, ID2));
            }

            else if(choice3.equals("2")){

                List<Account> allStockAccounts = customer.getAccountsByType(STOCK);
                Account stockAccount = allStockAccounts.get(0);
                String ID2 = stockAccount.getAccountId();
                if(choice2.equals("1")){ stockAccount.addToBalance(CurrencyType.USD, value);}
                else if(choice2.equals("2")){stockAccount.addToBalance(CurrencyType.EUR, value);}
                else if(choice2.equals("3")){stockAccount.addToBalance(CurrencyType.CAD, value);}
                else if(choice2.equals("4")){stockAccount.addToBalance(CurrencyType.JPY, value);}
                writer.updateAccountToDisk(stockAccount);
                writer.writeTxn(recordTransaction(value, customer.getId(), ID1, ID2));
            }
        }

        CustomerBalance.run(customer, currency, stockMarket);
    }

    public static void feeToBank(CurrencyType currencyType) throws IOException {

        AccountFactory accountFactory = new AccountFactory();
        List<Account> allAdminAccounts = accountFactory.produceAccountsByType(ADMIN);
        Account adminAccount = allAdminAccounts.get(0);
        if(currencyType.equals(CurrencyType.USD)){adminAccount.addToBalance(CurrencyType.USD, 5.00);}
        else if(currencyType.equals(CurrencyType.EUR)){adminAccount.addToBalance(CurrencyType.EUR, 5.00);}
        else if(currencyType.equals(CurrencyType.CAD)){adminAccount.addToBalance(CurrencyType.CAD, 5.00);}
        else if(currencyType.equals(CurrencyType.JPY)){adminAccount.addToBalance(CurrencyType.JPY, 5.00);}

        Writer writer = new Writer();
        writer.updateAccountToDisk(adminAccount);
    }

    public static Transaction recordTransaction(Double amount, String cusID, String from, String to){

        String ID = getRandomNumberString();
        String customerID = cusID;
        Double value = -amount;
        LocalDate date = LocalDate.now();
        String fromID = from;
        String toID = to;
        TransferTxn transaction = new TransferTxn(ID, date, value, cusID, fromID, cusID, toID);

        return transaction;
    }

    public static String getRandomNumberString() {

        // It will generate 6 digit random Number.
        // from 0 to 999999
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        // this will convert any number sequence into 6 character.
        return String.format("%06d", number);
    }
}
