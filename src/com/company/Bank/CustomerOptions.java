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

        Boolean presence = parser.checkPresence(name, password, true) >= 0;

        if(!presence){

            System.out.println();
            System.out.println("You are not a customer !");
            BankATM.run();

        }

        if(presence) {
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
        System.out.println("<4> Take / Pay Back Loan(s)");
        System.out.println("<5> Buy / Sell Stocks");
        System.out.println("<6> Return to Previous Menu");
        System.out.println("<q> Quit");

        String choice = input.next();

        while(!choice.equals("1") && !choice.equals("2") && !choice.equals("3") && !choice.equals("4") && !choice.equals("5") && !choice.equals("6") && !choice.equals("q")){

            System.out.println();
            System.out.println(" You've entered an incorrect option !");
            System.out.println();
            System.out.println("What do you want to do ?");
            System.out.println("<1> Check Balance");
            System.out.println("<2> Deposit");
            System.out.println("<3> Withdraw");
            System.out.println("<4> Take / Pay Back Loan(s)");
            System.out.println("<5> Buy / Sell Stocks");
            System.out.println("<6> Return to Previous Menu");
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
            CustomerLoan.run(customer);
        }

        else if(choice.equals("5")){
            CustomerStock.run(customer);
        }

        else if(choice.equals("6")){
            BankATM.run();
        }

        else if(choice.equals("q")){
            Printer.printExitMessage();
        }
    }
}
