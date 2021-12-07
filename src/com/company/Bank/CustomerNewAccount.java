package com.company.Bank;

import com.company.Account.Account;
import com.company.Account.AccountType;
import com.company.Persons.Customer;

import java.io.IOException;
import java.util.*;

public class CustomerNewAccount {



    public static void run(Customer customer, Currency currency) throws IOException {

        Scanner input = new Scanner(System.in);

        System.out.println();
        System.out.println("Enter type of account to be opened : ");
        System.out.println("<1> Savings");
        System.out.println("<2> Checkings");
        String choice = input.next();

        while(!choice.equals("1") && !choice.equals("2")){

            System.out.println("You've entered an incorrect option !");
            System.out.println("Enter type of account to be opened : ");
            System.out.println("<1> Savings");
            System.out.println("<2> Checkings");
            choice = input.next();
        }

        System.out.println("Enter the amount to be deposited : ");
        double value = input.nextDouble();

        if(choice.equals("1")){

            String accountNo = getRandomNumberString();
            Account newAccount = new Account(accountNo, customer.getName(), customer.getPwd(), AccountType.SAVINGS, value);
            customer.addAccount(newAccount);

        }

        else if(choice.equals("2")){

            String accountNo = getRandomNumberString();
            Account newAccount = new Account(accountNo, customer.getName(), customer.getPwd(), AccountType.CHECKINGS, value);
            customer.addAccount(newAccount);
        }

        CustomerBalance.run(customer, currency);
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
