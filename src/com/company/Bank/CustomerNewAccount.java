package com.company.Bank;

import com.company.Account.Account;
import com.company.Account.AccountType;
import com.company.Factories.AccountFactory;
import com.company.Persons.Customer;
import com.company.Stock.StockMarket;
import com.company.Utils.Writer;

import java.io.IOException;
import java.util.*;

import static com.company.Account.AccountType.ADMIN;

public class CustomerNewAccount {



    public static void run(Customer customer, Currency currency, StockMarket stockMarket) throws IOException {

        AccountFactory accountFactory = new AccountFactory();
        Writer writer = new Writer();
        Scanner input = new Scanner(System.in);

        System.out.println();
        System.out.println("Enter type of account to be opened : ");
        System.out.println("<1> Savings");
        System.out.println("<2> Checkings");
        System.out.println("<3> Stock");
        String choice1 = input.next();

        while(!choice1.equals("1") && !choice1.equals("2") && !choice1.equals("3")){

            System.out.println("You've entered an incorrect option !");
            System.out.println("Enter type of account to be opened : ");
            System.out.println("<1> Savings");
            System.out.println("<2> Checkings");
            System.out.println("<3> Stock");

            choice1 = input.next();
        }

        if(choice1.equals("1")){

            List<Account> allStockAccounts = customer.getAccountsByType(AccountType.STOCK);
            if(!allStockAccounts.isEmpty()){
                System.out.println("You already have a stock account !");
            }
            else{

                String accountNo = getRandomNumberString();
                String values = "USD" + " " + 0.0 + " " + "EUR" + " " + 0.0 + " " + "CAD" + " " + 0.0 + " " + "JPY" + " " + 0.0;
                String[] args = {accountNo, customer.getName(), customer.getPwd(), "STOCK", values};
                Account newAccount = accountFactory.produceAccount(args);
                customer.addAccount(newAccount);
                writer.grantNewAccount(customer, newAccount, true);

            }
        }
        else{

            System.out.println();
            System.out.println("Enter the currency you want to deposit : ");
            System.out.println("<1> USD");
            System.out.println("<2> EUR");
            System.out.println("<3> CAD");
            System.out.println("<4> JPY");
            String choice2 = input.next();

            while(!choice2.equals("1") && !choice2.equals("2") && !choice2.equals("3") && !choice2.equals("4")){

                System.out.println("You've entered an incorrect option !");
                System.out.println("Enter the currency you want to deposit : ");
                System.out.println("<1> USD");
                System.out.println("<2> EUR");
                System.out.println("<3> CAD");
                System.out.println("<4> JPY");
                choice2 = input.next();
            }


            System.out.println("Enter the amount to be deposited : ");
            double value = input.nextDouble();
            value =- 5.0;
            String accountNo = getRandomNumberString();

            if(choice1.equals("1")){

                String values = null;
                if(choice2.equals("1")){values = "USD" + " " + value + " " + "EUR" + " " + 0.0 + " " + "CAD" + " " + 0.0 + " " + "JPY" + " " + 0.0; feeToBank(CurrencyType.USD); }
                else if(choice2.equals("2")){values = "USD" + " " + 0.0 + " " + "EUR" + " " + value + " " + "CAD" + " " + 0.0 + " " + "JPY" + " " + 0.0; feeToBank(CurrencyType.EUR);}
                else if(choice2.equals("3")){values = "USD" + " " + 0.0 + " " + "EUR" + " " + 0.0 + " " + "CAD" + " " + value + " " + "JPY" + " " + 0.0; feeToBank(CurrencyType.CAD);}
                else if(choice2.equals("4")){values = "USD" + " " + 0.0 + " " + "EUR" + " " + 0.0 + " " + "CAD" + " " + 0.0 + " " + "JPY" + " " + value; feeToBank(CurrencyType.JPY);}
                String[] args = {accountNo, customer.getName(), customer.getPwd(), "SAVINGS", values};
                Account newAccount = accountFactory.produceAccount(args);
                customer.addAccount(newAccount);
                writer.grantNewAccount(customer, newAccount, true);

            }

            else if(choice1.equals("2")){

                String values = null;
                if(choice2.equals("1")){values = "USD" + " " + value + " " + "EUR" + " " + 0.0 + " " + "CAD" + " " + 0.0 + " " + "JPY" + " " + 0.0; feeToBank(CurrencyType.USD);}
                else if(choice2.equals("2")){values = "USD" + " " + 0.0 + " " + "EUR" + " " + value + " " + "CAD" + " " + 0.0 + " " + "JPY" + " " + 0.0; feeToBank(CurrencyType.EUR);}
                else if(choice2.equals("3")){values = "USD" + " " + 0.0 + " " + "EUR" + " " + 0.0 + " " + "CAD" + " " + value + " " + "JPY" + " " + 0.0; feeToBank(CurrencyType.CAD);}
                else if(choice2.equals("4")){values = "USD" + " " + 0.0 + " " + "EUR" + " " + 0.0 + " " + "CAD" + " " + 0.0 + " " + "JPY" + " " + value; feeToBank(CurrencyType.JPY);}
                String[] args = {accountNo, customer.getName(), customer.getPwd(), "CHECKINGS", values};
                System.out.println(values);
                Account newAccount = accountFactory.produceAccount(args);
                customer.addAccount(newAccount);
                writer.grantNewAccount(customer, newAccount, true);
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

    public static String getRandomNumberString() {
        // It will generate 6 digit random Number.
        // from 0 to 999999
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        // this will convert any number sequence into 6 character.
        return String.format("%06d", number);
    }
}
