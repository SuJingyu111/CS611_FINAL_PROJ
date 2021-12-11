package com.company.Bank;

import com.company.Account.Account;
import com.company.Account.AccountType;
import com.company.Account.LoanAccount;
import com.company.Factories.AccountFactory;
import com.company.Persons.Customer;
import com.company.Stock.StockMarket;
import com.company.Transactions.LoanTxn;
import com.company.Transactions.Transaction;
import com.company.Utils.Writer;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class CustomerLoan {

    public static void run(Customer customer, Currency currency, StockMarket stockMarket) throws IOException {

        Scanner input = new Scanner(System.in);
        Writer writer = new Writer();

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

        if(allLoanAccounts.isEmpty()){
            String accountNo = getRandomNumberString();
            String[] args = {accountNo, customer.getName(), customer.getPwd(), "LOAN", " ", " "};
            AccountFactory accountFactory = new AccountFactory();
            Account newAccount = accountFactory.produceAccount(args);
            customer.addAccount(newAccount);
            writer.grantNewAccount(customer, newAccount, true);
        }

        allLoanAccounts = customer.getAccountsByType(AccountType.LOAN);

        List<Account> allCheckingsAccounts = customer.getAccountsByType(AccountType.CHECKINGS);
        List<Account> allSavingsAccounts = customer.getAccountsByType(AccountType.SAVINGS);

        Map<LocalDate, Double> amountsDue = new HashMap<>();

        for(Account loanAccount : allLoanAccounts){
            amountsDue = ((LoanAccount) loanAccount).getAmountsDue();
        }



        if(choice1.equals("1")){

            for(Account loanAccount : allLoanAccounts){
                ((LoanAccount) loanAccount).getLoan(LocalDate.now(), value);
                writer.updateAccountToDisk(loanAccount);
                writer.writeTxn(recordTransaction(value, customer.getId()));
                amountsDue = ((LoanAccount) loanAccount).getAmountsDue();
            }

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
                    writer.updateAccountToDisk(account);
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
                        writer.updateAccountToDisk(account);
                    }
                }

                for(Account loanAccount : allLoanAccounts){
                    ((LoanAccount)loanAccount).payLoan(date, value);
                    writer.updateAccountToDisk(loanAccount);
                    writer.writeTxn(recordTransaction(-value, customer.getId()));
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
                        writer.updateAccountToDisk(account);
                    }
                }

                for(Account loanAccount : allLoanAccounts){
                    ((LoanAccount)loanAccount).payLoan(date, value);
                    writer.updateAccountToDisk(loanAccount);
                    writer.writeTxn(recordTransaction(-value, customer.getId()));
                }
            }

        }

        //writer.writeTxn(recordTransaction(value, customer.getId()));
        CustomerBalance.run(customer, currency, stockMarket);

    }

    public static Transaction recordTransaction(Double amount, String cusID){

        String ID = getRandomNumberString();
        String customerID = cusID;
        Double value = amount;
        LocalDate date = LocalDate.now();
        LoanTxn transaction = new LoanTxn(ID,date, amount, customerID);
        return transaction;
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
