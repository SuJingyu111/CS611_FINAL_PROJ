package com.company.Bank;

import com.company.Account.Account;
import com.company.Persons.Customer;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import static com.company.Account.AccountType.CHECKINGS;
import static com.company.Account.AccountType.SAVINGS;

public class CustomerTransfer {

    public static void run(Customer customer, Currency currency) throws IOException {

        Scanner input = new Scanner(System.in);

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
        System.out.println("Enter the amount : ");
        double value  = input.nextDouble();

        if(choice1.equals("1")){

            List<Account> AllCheckingsAccounts = customer.getAccountsByType(CHECKINGS);
            System.out.println();
            System.out.println("********************************************************************************************");
            System.out.println("           Account Id      Balance");
            int count = 1;
            for(Account account : AllCheckingsAccounts){
                System.out.println("<" + count + "> " + "           " +  account.getAccountId() + "           " + account.getBalance());
            }

            System.out.println();
            System.out.println("Enter the account ID to be transferred from :  ");
            String ID = input.next();

            for(Account account : AllCheckingsAccounts){
                if(account.getAccountId().equals(ID)){
                    account.setBalance((account.getBalance() - value));
                }
            }

            List<Account> AllSavingsAccounts = customer.getAccountsByType(SAVINGS);
            System.out.println();
            System.out.println("********************************************************************************************");
            System.out.println("           Account Id      Balance");
            count = 1;
            for(Account account : AllSavingsAccounts){
                System.out.println("<" + count + "> " + "           " +  account.getAccountId() + "           " + account.getBalance());
            }

            System.out.println();
            System.out.println("Enter the account ID to be transferred to :  ");
            ID = input.next();

            for(Account account : AllSavingsAccounts){
                if(account.getAccountId().equals(ID)){
                    account.setBalance((account.getBalance() + value));
                }
            }
        }

        else if(choice1.equals("2")) {

            List<Account> AllSavingsAccounts = customer.getAccountsByType(SAVINGS);
            System.out.println();
            System.out.println("********************************************************************************************");
            System.out.println("           Account Id      Balance");
            int count = 1;
            for (Account account : AllSavingsAccounts) {
                System.out.println("<" + count + "> " + "           " + account.getAccountId() + "           " + account.getBalance());
            }

            System.out.println();
            System.out.println("Enter the account ID to be transferred from :  ");
            String ID = input.next();

            for (Account account : AllSavingsAccounts) {
                if (account.getAccountId().equals(ID)) {
                    account.setBalance((account.getBalance() - value));
                }
            }

            List<Account> AllCheckingsAccounts = customer.getAccountsByType(CHECKINGS);
            System.out.println();
            System.out.println("********************************************************************************************");
            System.out.println("           Account Id      Balance");
            count = 1;
            for (Account account : AllCheckingsAccounts) {
                System.out.println("<" + count + "> " + "           " + account.getAccountId() + "           " + account.getBalance());
            }

            System.out.println();
            System.out.println("Enter the account ID to be transferred to :  ");
            ID = input.next();

            for (Account account : AllCheckingsAccounts) {
                if (account.getAccountId().equals(ID)) {
                    account.setBalance((account.getBalance() - value));
                }
            }
        }

        //TODO : Add a method in writer class to update the changes in the balance in the csv files
        //TODO : add transaction to transaction.csv
        CustomerBalance.run(customer, currency);
    }
}
