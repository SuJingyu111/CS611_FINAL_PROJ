package com.company.Bank;

import com.company.Account.Account;
import com.company.Persons.Customer;

import java.io.IOException;
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

            Account account1 = customer.getAccount(CHECKINGS);
            account1.setBalance((account1.getBalance() - value));
            Account account2 = customer.getAccount(SAVINGS);
            account2.setBalance((account2.getBalance() + value));
        }

        else if(choice1.equals("2")){

            Account account1 = customer.getAccount(SAVINGS);
            account1.setBalance((account1.getBalance() - value));
            Account account2 = customer.getAccount(CHECKINGS);
            account2.setBalance((account2.getBalance() + value));
        }

        //TODO : Add a method in writer class to update the changes in the balance in the csv files
        CustomerBalance.run(customer, currency);
    }
}
