package com.company.Bank;

import com.company.Account.Account;
import com.company.Account.AccountType;
import com.company.Persons.Customer;

import java.io.IOException;
import java.util.*;

public class CustomerLoan {

    public static void run(Customer customer, Currency currency) throws IOException {

        Scanner input = new Scanner(System.in);

        System.out.println();
        System.out.println("What do you want to do ?");
        System.out.println("<1> Take a loan");
        System.out.println("<2> Pay back a loan");
        String choice1 = input.next();

        while(!choice1.equals("1") && !choice1.equals("2")){
            System.out.println("You've entered the wrong input !");
            System.out.println("What do you want to do ?");
            System.out.println("<1> Take a loan");
            System.out.println("<2> Pay back a loan");
            choice1 = input.next();
        }

        System.out.println("Enter the amount : ");
        double value = input.nextDouble();

        //TODO: ADJUST FUNCTION CALL
        Account loanAccount = customer.getAccount(AccountType.LOAN);
        Account checkingsAccount = customer.getAccount(AccountType.CHECKINGS);
        Account savingsAccount = customer.getAccount(AccountType.SAVINGS);

        if(choice1.equals("1")){
            loanAccount.setBalance(loanAccount.getBalance() + value);
            checkingsAccount.setBalance(checkingsAccount.getBalance() + value);
        }

        else if (choice1.equals("2")){
            System.out.println("Enter the account you want to pay from : ");
            System.out.println("<1> Checkings");
            System.out.println("<2> Savings");
            String choice2 = input.next();

            while(!choice1.equals("1") && !choice1.equals("2")){
                System.out.println("You've entered the wrong input !");
                System.out.println("Enter the account you want to pay from : ");
                System.out.println("<1> Checkings");
                System.out.println("<2> Savings");
                choice2 = input.next();
            }

            if(choice2.equals("1")){
                checkingsAccount.setBalance(checkingsAccount.getBalance() - value);
                loanAccount.setBalance(loanAccount.getBalance() - value);
            }

            else if (choice2.equals("2")){
                savingsAccount.setBalance(savingsAccount.getBalance() - value);
                loanAccount.setBalance(loanAccount.getBalance() - value);
            }

        }

        CustomerBalance.run(customer, currency);


    }

}
