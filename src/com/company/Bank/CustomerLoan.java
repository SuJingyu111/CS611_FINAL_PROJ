package com.company.Bank;

import com.company.Account.Account;
import com.company.Account.AccountType;
import com.company.Account.LoanAccount;
import com.company.Persons.Customer;

import java.io.IOException;
import java.time.LocalDate;
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

        System.out.println("Enter the amount in USD : ");
        double value = input.nextDouble();

        List<Account> allLoanAccounts = customer.getAccountsByType(AccountType.LOAN);
        List<Account> allCheckingsAccounts = customer.getAccountsByType(AccountType.CHECKINGS);
        List<Account> allSavingsAccounts = customer.getAccountsByType(AccountType.SAVINGS);

        Map<LocalDate, Double> amountsDue = null;

        for(Account loanAccount : allLoanAccounts){
            ((LoanAccount) loanAccount).getLoan(LocalDate.now(), value);
            amountsDue = ((LoanAccount) loanAccount).getAmountsDue();
        }


        if(choice1.equals("1")){

            System.out.println();
            System.out.println("Current Loans : ");
            System.out.println();
            System.out.println("********************************************************************************************");
            System.out.println("           Date            Balance");
            int count = 1;
            for (Map.Entry<LocalDate, Double> entry : amountsDue.entrySet()) {
                System.out.println("<" + count + ">" + "          " + entry.getKey() + "         " + entry.getValue());
                count += 1;
            }
            System.out.println("********************************************************************************************");

            System.out.println();
            System.out.println("********************************************************************************************");
            System.out.println("           Account Id      Currency      Balance");
            count = 1;
            for(Account account : allCheckingsAccounts){
                System.out.println("<" + count + "> " + "           " +  account.getAccountId());
                Map<CurrencyType, Double> map = account.getBalance();
                for (Map.Entry<CurrencyType, Double> entry : map.entrySet()){
                    System.out.println("                    " + entry.getKey() + "        " + entry.getValue());
                }
                count += 1;
            }
            System.out.println("********************************************************************************************");

            System.out.println();
            System.out.println("Enter the account ID you want the amount to be deposited  :  ");
            String ID = input.next();

            for(Account account : allCheckingsAccounts){
                if(account.getAccountId().equals(ID)){
                    account.addToBalance(CurrencyType.USD, value);
                }
            }
        }

        else if (choice1.equals("2")){

            System.out.println();
            System.out.println("Current Loans : ");
            System.out.println();
            System.out.println("********************************************************************************************");
            System.out.println("           Date            Balance");
            int count = 1;
            for (Map.Entry<LocalDate, Double> entry : amountsDue.entrySet()) {
                System.out.println("<" + count + ">" + "          " + entry.getKey() + "         " + entry.getValue());
                count += 1;
            }
            System.out.println("********************************************************************************************");

            System.out.println();
            System.out.println("Enter date of the loan you want to pay back : (YYYY-MM-DD)");
            String date1 = input.next();
            LocalDate date = LocalDate.parse(date1);

            System.out.println("Enter the account you want to pay from : ");
            System.out.println("<1> Checkings");
            System.out.println("<2> Savings");
            String choice2 = input.next();

            while(!choice2.equals("1") && !choice2.equals("2")){
                System.out.println("You've entered the wrong input !");
                System.out.println("Enter the account you want to pay from : ");
                System.out.println("<1> Checkings");
                System.out.println("<2> Savings");
                choice2 = input.next();
            }

            if(choice2.equals("1")){

                System.out.println();
                System.out.println("********************************************************************************************");
                System.out.println("           Account Id      Currency      Balance");
                count = 1;
                for(Account account : allCheckingsAccounts){
                    System.out.println("<" + count + "> " + "           " +  account.getAccountId());
                    Map<CurrencyType, Double> map = account.getBalance();
                    for (Map.Entry<CurrencyType, Double> entry : map.entrySet()){
                        System.out.println("                    " + entry.getKey() + "        " + entry.getValue());
                    }
                    count += 1;
                }
                System.out.println("********************************************************************************************");

                System.out.println();
                System.out.println("Enter the account ID you want to pay back from :  ");
                String ID = input.next();

                for(Account account : allCheckingsAccounts){
                    if(account.getAccountId().equals(ID)){
                        account.addToBalance(CurrencyType.USD, -value);
                    }
                }

                for(Account loanAccount : allLoanAccounts){
                    ((LoanAccount)loanAccount).payLoan(date, value);
                }
            }

            else if (choice2.equals("2")){

                System.out.println();
                System.out.println("********************************************************************************************");
                System.out.println("           Account Id      Currency      Balance");
                count = 1;
                for(Account account : allSavingsAccounts){
                    System.out.println("<" + count + "> " + "           " +  account.getAccountId());
                    Map<CurrencyType, Double> map = account.getBalance();
                    for (Map.Entry<CurrencyType, Double> entry : map.entrySet()){
                        System.out.println("                    " + entry.getKey() + "        " + entry.getValue());
                    }
                    count += 1;
                }
                System.out.println("********************************************************************************************");

                System.out.println();
                System.out.println("Enter the account ID you want to pay back from :  ");
                String ID = input.next();

                for(Account account : allSavingsAccounts){
                    if(account.getAccountId().equals(ID)){
                        account.addToBalance(CurrencyType.USD, -value);
                    }
                }

                for(Account loanAccount : allLoanAccounts){
                    ((LoanAccount)loanAccount).payLoan(date, value);
                }
            }

        }

        CustomerBalance.run(customer, currency);

    }

}
