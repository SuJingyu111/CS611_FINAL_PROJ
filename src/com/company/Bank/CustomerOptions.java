package com.company.Bank;

import com.company.Factories.PersonFactory;
import com.company.Persons.Customer;
import java.io.IOException;
import java.util.Scanner;

public class CustomerOptions {

    public static void run(Currency currency) throws IOException {

        Scanner input = new Scanner(System.in);
        Parser parser = new Parser();

        System.out.println("Please enter your name : ");
        String name  = input.next();
        System.out.println("Please enter your password : ");
        String password = input.next();

        int presence = parser.checkPresence(name, password, true);

        if(presence == -1){

            System.out.println();
            System.out.println("You are not a customer !");
            BankATM.run();

        }

        else if (presence != -1) {
            PersonFactory personFactory = new PersonFactory();
            Customer customer = personFactory.produceCustomer(name, password);
            System.out.println();
            System.out.println("****************************************************************************************");
            System.out.println("Hello there, " + customer.getName() + " !");
            System.out.println("****************************************************************************************");
            System.out.println();
            options(customer, currency);

        }

    }

    public static void options(Customer customer, Currency currency) throws IOException {

        Scanner input = new Scanner(System.in);
        System.out.println("What do you want to do ?");
        System.out.println("<1> Check Balance");
        System.out.println("<2> Deposit");
        System.out.println("<3> Withdraw");
        System.out.println("<4> Transfer Between Accounts");
        System.out.println("<5> Take / Pay Back Loan(s)");
        System.out.println("<6> Buy / Sell Stocks");
        System.out.println("<7> Create New Account");
        System.out.println("<8> Close Account");
        System.out.println("<9> Return to Previous Menu");
        System.out.println("<q> Quit");

        String choice = input.next();

        while(!choice.equals("1") && !choice.equals("2") && !choice.equals("3") && !choice.equals("4")
                && !choice.equals("5") && !choice.equals("6")  && !choice.equals("7") && !choice.equals("q")){

            System.out.println();
            System.out.println(" You've entered an incorrect option !");
            System.out.println();
            System.out.println("What do you want to do ?");
            System.out.println("<1> Check Balance");
            System.out.println("<2> Deposit");
            System.out.println("<3> Withdraw");
            System.out.println("<4> Transfer Between Accounts");
            System.out.println("<5> Take / Pay Back Loan(s)");
            System.out.println("<6> Buy / Sell Stocks");
            System.out.println("<7> Create New Account");
            System.out.println("<8> Close Account");
            System.out.println("<9> Return to Previous Menu");
            System.out.println("<q> Quit");
            choice = input.next();
        }

        if(choice.equals("1")){
            CustomerBalance.run(customer, currency);
        }

        else if(choice.equals("2")){
            CustomerDeposit.run(customer, currency);
        }

        else if(choice.equals("3")){
            CustomerWithdraw.run(customer, currency);
        }

        else if(choice.equals("4")){
            CustomerTransfer.run(customer, currency);
        }

        else if(choice.equals("5")){
            CustomerLoan.run(customer, currency);
        }

        else if(choice.equals("6")){
            CustomerStock.run(customer);
        }

        else if(choice.equals("7")){
            CustomerNewAccount.run(customer, currency);
        }

        else if(choice.equals("8")){
            CustomerCloseAccount.run(customer, currency);
        }

        else if(choice.equals("9")){
            BankATM.run();
        }

        else if(choice.equals("q")){
            Printer.printExitMessage();
        }
    }
}
