package com.company.Bank;

import com.company.Account.Account;
import com.company.Persons.Customer;


import java.io.IOException;
import java.util.Scanner;

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
            Account account = customer.getAccount(SAVINGS);
            account.setBalance((account.getBalance() + value));
        }
        else if(choice2.equals("2")){
            Account account = customer.getAccount(CHECKINGS);
            account.setBalance((account.getBalance() + value));
        }

        CustomerBalance.run(customer,currency);

    }
}
