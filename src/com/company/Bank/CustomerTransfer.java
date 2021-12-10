package com.company.Bank;

import com.company.Account.Account;
import com.company.Persons.Customer;
import com.company.Transactions.DepositOrWithdrawTxn;
import com.company.Transactions.Transaction;
import com.company.Transactions.TransferTxn;
import com.company.Utils.Writer;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static com.company.Account.AccountType.CHECKINGS;
import static com.company.Account.AccountType.SAVINGS;

public class CustomerTransfer {

    public static void run(Customer customer, Currency currency) throws IOException {

        Scanner input = new Scanner(System.in);
        Writer writer = new Writer();

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
            String ID1 = input.next();

            for(Account account : AllCheckingsAccounts){
                if(account.getAccountId().equals(ID1)){
                    account.setBalance((account.getBalance() - value));
                    writer.updateAccountToDisk(account);
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
            String ID2 = input.next();

            for(Account account : AllSavingsAccounts){
                if(account.getAccountId().equals(ID2)){
                    account.setBalance((account.getBalance() + value));
                    writer.updateAccountToDisk(account);
                }
            }

            writer.writeTxn(recordTransaction(value, customer.getId(), ID1, ID2));
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
            String ID1 = input.next();

            for (Account account : AllSavingsAccounts) {
                if (account.getAccountId().equals(ID1)) {
                    account.setBalance((account.getBalance() - value));
                    writer.updateAccountToDisk(account);
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
            String ID2 = input.next();

            for (Account account : AllCheckingsAccounts) {
                if (account.getAccountId().equals(ID2)) {
                    account.setBalance((account.getBalance() - value));
                    writer.updateAccountToDisk(account);
                }
            }
            writer.writeTxn(recordTransaction(value, customer.getId(), ID1, ID2));
        }

        CustomerBalance.run(customer, currency);
    }

    public static Transaction recordTransaction(Double amount, String cusID, String from, String to){

        String ID = getRandomNumberString();
        String customerID = cusID;
        Double value = -amount;
        LocalDate date = LocalDate.now();
        String fromID = from;
        String toID = to;
        TransferTxn transaction = new TransferTxn(ID, date, value, cusID, fromID, cusID, toID);

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
