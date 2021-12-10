package com.company.Bank;

import com.company.Account.Account;
import com.company.Account.AccountType;
import com.company.Factories.AccountFactory;
import com.company.Persons.Customer;
import com.company.Utils.Writer;

import java.io.IOException;
import java.util.*;

public class CustomerNewAccount {



    public static void run(Customer customer, Currency currency) throws IOException {

        AccountFactory accountFactory = new AccountFactory();
        Writer writer = new Writer();
        Scanner input = new Scanner(System.in);

        System.out.println();
        System.out.println("Enter type of account to be opened : ");
        System.out.println("<1> Savings");
        System.out.println("<2> Checkings");
        String choice1 = input.next();

        while(!choice1.equals("1") && !choice1.equals("2")){

            System.out.println("You've entered an incorrect option !");
            System.out.println("Enter type of account to be opened : ");
            System.out.println("<1> Savings");
            System.out.println("<2> Checkings");
            choice1 = input.next();
        }

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
        String accountNo = getRandomNumberString();

        if(choice1.equals("1")){

            String values = null;
            if(choice2.equals("1")){values = "USD" + " " + value + " " + "EUR" + " " + 0.0 + " " + "CAD" + " " + 0.0 + " " + "JPY" + " " + 0.0;}
            else if(choice2.equals("2")){values = "USD" + " " + 0.0 + " " + "EUR" + " " + value + " " + "CAD" + " " + 0.0 + " " + "JPY" + " " + 0.0;}
            else if(choice2.equals("3")){values = "USD" + " " + 0.0 + " " + "EUR" + " " + 0.0 + " " + "CAD" + " " + value + " " + "JPY" + " " + 0.0;}
            else if(choice2.equals("4")){values = "USD" + " " + 0.0 + " " + "EUR" + " " + 0.0 + " " + "CAD" + " " + 0.0 + " " + "JPY" + " " + value;}
            String[] args = {accountNo, customer.getName(), customer.getPwd(), "SAVINGS", values};
            Account newAccount = accountFactory.produceAccount(args);
            customer.addAccount(newAccount);
            writer.grantNewAccount(customer, newAccount, true);

        }

        else if(choice1.equals("2")){

            String values = null;
            if(choice2.equals("1")){values = "USD" + " " + value + " " + "EUR" + " " + 0.0 + " " + "CAD" + " " + 0.0 + " " + "JPY" + " " + 0.0;}
            else if(choice2.equals("2")){values = "USD" + " " + 0.0 + " " + "EUR" + " " + value + " " + "CAD" + " " + 0.0 + " " + "JPY" + " " + 0.0;}
            else if(choice2.equals("3")){values = "USD" + " " + 0.0 + " " + "EUR" + " " + 0.0 + " " + "CAD" + " " + value + " " + "JPY" + " " + 0.0;}
            else if(choice2.equals("4")){values = "USD" + " " + 0.0 + " " + "EUR" + " " + 0.0 + " " + "CAD" + " " + 0.0 + " " + "JPY" + " " + value;}
            String[] args = {accountNo, customer.getName(), customer.getPwd(), "CHECKINGS", values};
            System.out.println(values);
            Account newAccount = accountFactory.produceAccount(args);
            customer.addAccount(newAccount);
            writer.grantNewAccount(customer, newAccount, true);
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
