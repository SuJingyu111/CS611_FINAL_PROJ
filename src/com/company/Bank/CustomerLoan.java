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

        List<Account> allLoanAccounts = customer.getAccountsByType(AccountType.LOAN);
        List<Account> allCheckingsAccounts = customer.getAccountsByType(AccountType.CHECKINGS);
        List<Account> allSavingsAccounts = customer.getAccountsByType(AccountType.SAVINGS);

        if(choice1.equals("1")){

            //TODO: MODIFY FUNCTION CALL
            for(Account loanAccount : allLoanAccounts){
                loanAccount.setBalance(loanAccount.getBalance() + value);
            }
            System.out.println();
            System.out.println("********************************************************************************************");
            System.out.println("           Account Id      Balance");
            int count = 1;
            for(Account account : allCheckingsAccounts){
                System.out.println("<" + count + "> " + "           " +  account.getAccountId() + "           " + account.getBalance());
            }

            System.out.println();
            System.out.println("Enter the account ID you want the amount to be deposited  :  ");
            String ID = input.next();

            //TODO: MODIFY FUNCTION CALL
            for(Account account : allCheckingsAccounts){
                if(account.getAccountId().equals(ID)){
                    account.setBalance((account.getBalance() + value));
                }
            }
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

                System.out.println();
                System.out.println("********************************************************************************************");
                System.out.println("           Account Id      Balance");
                int count = 1;
                for(Account account : allCheckingsAccounts){
                    System.out.println("<" + count + "> " + "           " +  account.getAccountId() + "           " + account.getBalance());
                }

                System.out.println();
                System.out.println("Enter the account ID you want to pay back from :  ");
                String ID = input.next();

                //TODO: MODIFY FUNCTION CALL
                for(Account account : allCheckingsAccounts){
                    if(account.getAccountId().equals(ID)){
                        account.setBalance((account.getBalance() - value));
                    }
                }
                //TODO: MODIFY FUNCTION CALL
                for(Account loanAccount : allLoanAccounts){
                    loanAccount.setBalance(loanAccount.getBalance() - value);
                }
            }

            else if (choice2.equals("2")){

                System.out.println();
                System.out.println("********************************************************************************************");
                System.out.println("           Account Id      Balance");
                int count = 1;
                for(Account account : allSavingsAccounts){
                    System.out.println("<" + count + "> " + "           " +  account.getAccountId() + "           " + account.getBalance());
                }

                System.out.println();
                System.out.println("Enter the account ID you want to pay back from :  ");
                String ID = input.next();

                //TODO: MODIFY FUNCTION CALL
                for(Account account : allSavingsAccounts){
                    if(account.getAccountId().equals(ID)){
                        account.setBalance((account.getBalance() - value));
                    }
                }
                //TODO: MODIFY FUNCTION CALL
                for(Account loanAccount : allLoanAccounts){
                    loanAccount.setBalance(loanAccount.getBalance() - value);
                }
            }

        }

        CustomerBalance.run(customer, currency);

    }

}
