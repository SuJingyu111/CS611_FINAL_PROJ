package com.company.Bank;

import com.company.Account.Account;
import com.company.Persons.Customer;


import java.io.IOException;
import java.util.*;


import static com.company.Account.AccountType.*;

public class CustomerDeposit {

    public static void run(Customer customer, Currency currency) throws IOException {

        Scanner input = new Scanner(System.in);

        System.out.println();
        System.out.println("Please enter the currency you want to deposit : ");
        System.out.println("<1> USD");
        System.out.println("<2> EUR");
        System.out.println("<3> CAD");
        System.out.println("<4> JPY");
        String choice1 = input.next();

        if(!choice1.equals("1") && !choice1.equals("2") && !choice1.equals("3") && !choice1.equals("4")){

            System.out.println("You've entered the wrong input ! ");
            System.out.println();
            System.out.println("Please enter the currency you want to deposit : ");
            System.out.println("<1> USD");
            System.out.println("<2> EUR");
            System.out.println("<3> CAD");
            System.out.println("<4> JPY");
            choice1 = input.next();
        }

        System.out.println();
        System.out.println("Enter the amount you want to deposit : ");
        double value = input.nextInt();

        System.out.println();
        System.out.println("Please enter the account you want to deposit to :");
        System.out.println("<1> Savings");
        System.out.println("<2> Checkings");
        String choice2 = input.next();

        if(!choice2.equals("1") && !choice2.equals("2")){

            System.out.println("You've entered the wrong input ! ");
            System.out.println();
            System.out.println("Please enter the account you want to deposit to :");
            System.out.println("<1> Savings");
            System.out.println("<2> Checkings");
            choice2 = input.next();
        }

        if(choice1.equals("2")){
            value *= currency.getForex("EUR");
        }
        else if(choice1.equals("3")){
            value *= currency.getForex("CAD");
        }
        else if(choice1.equals("4")){
            value *= currency.getForex("JPY");
        }

        if(choice2.equals("1")){

            List<Account> AllSavingsAccounts = customer.getAccountsByType(SAVINGS);
            System.out.println();
            System.out.println("********************************************************************************************");
            System.out.println("           Account Id      Balance");
            int count = 1;
            for(Account account : AllSavingsAccounts){
                System.out.println("<" + count + "> " + "           " +  account.getAccountId() + "           " + account.getBalance());
            }

            System.out.println();
            System.out.println("Enter the account ID :  ");
            String ID = input.next();

            for(Account account : AllSavingsAccounts){
                if(account.getAccountId().equals(ID)){
                    account.setBalance((account.getBalance() + value));
                }
            }
        }

        else if(choice2.equals("2")){

            List<Account> AllCheckingsAccounts = customer.getAccountsByType(CHECKINGS);
            System.out.println();
            System.out.println("********************************************************************************************");
            System.out.println("           Account Id      Balance");
            int count = 1;
            for(Account account : AllCheckingsAccounts){
                System.out.println("<" + count + "> " + "           " +  account.getAccountId() + "           " + account.getBalance());
            }

            System.out.println();
            System.out.println("Enter the account ID :  ");
            String ID = input.next();

            for(Account account : AllCheckingsAccounts){
                if(account.getAccountId().equals(ID)){
                    account.setBalance((account.getBalance() + value));
                }
            }
        }

        //TODO : Add a method in writer class to update the changes in the balance in the csv files
        //TODO : add transaction to transaction.csv
        CustomerBalance.run(customer,currency);
    }
}
