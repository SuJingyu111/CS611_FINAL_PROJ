package com.company.Bank;

import com.company.Account.Account;
import com.company.Persons.Customer;

import java.io.IOException;
import java.util.Scanner;

import static com.company.Account.AccountType.CHECKINGS;
import static com.company.Account.AccountType.SAVINGS;

public class CustomerWithdraw {

    public static void run(Customer customer, Currency currency) throws IOException {

        Scanner input = new Scanner(System.in);

        System.out.println();
        System.out.println("Please enter the currency you want to withdraw : ");
        System.out.println("<1> USD");
        System.out.println("<2> EUR");
        System.out.println("<3> CAD");
        System.out.println("<4> JPY");
        String choice1 = input.next();

        if(!choice1.equals("1") && !choice1.equals("2") && !choice1.equals("3") && !choice1.equals("4")){

            System.out.println("You've entered the wrong input ! ");
            System.out.println();
            System.out.println("Please enter the currency you want to withdraw : ");
            System.out.println("<1> USD");
            System.out.println("<2> EUR");
            System.out.println("<3> CAD");
            System.out.println("<4> JPY");
            choice1 = input.next();
        }

        System.out.println();
        System.out.println("Enter the amount you want to withdraw : ");
        double value = input.nextInt();


        if(choice1.equals("2")){
            value *= currency.getForex("EUR");
        }
        else if(choice1.equals("3")){
            value *= currency.getForex("CAD");
        }
        else if(choice1.equals("4")){
            value *= currency.getForex("JPY");
        }
        //TODO: ADJUST FUNCTION CALL
        Account account = customer.getAccount(CHECKINGS);
        account.setBalance((account.getBalance() - value));

        CustomerBalance.run(customer,currency);
    }
}
