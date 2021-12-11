package com.company.Bank.ManagerAllOptions;

import com.company.Bank.BankATM;
import com.company.Currency.Currency;
import com.company.Factories.PersonFactory;
import com.company.Persons.Manager;

import java.io.IOException;
import java.util.Scanner;

import com.company.Stock.StockMarket;
import com.company.Utils.Parser;
import com.company.Utils.Printer;

public class ManagerOptions {

    public static void run(Currency currency, StockMarket stockMarket) throws IOException {

        Scanner input = new Scanner(System.in);
        Parser parser = new Parser();

        System.out.println("Please enter your name : ");
        String name  = input.next();
        System.out.println("Please enter your password : ");
        String password = input.next();

        int presence = parser.checkPresence(name, password, false);

        if(presence == -1){

            System.out.println();
            System.out.println("You are not a manager !");
            BankATM.run();

        }

        else if (presence != -1) {
            PersonFactory personFactory = new PersonFactory();
            Manager manager = personFactory.produceManager(name, password);
            System.out.println();
            System.out.println("****************************************************************************************");
            System.out.println("Hello there, " + manager.getName() + " !");
            System.out.println("****************************************************************************************");
            System.out.println();
            options(manager, currency, stockMarket);

        }

    }

    public static void options(Manager manager, Currency currency, StockMarket stockMarket) throws IOException {

        Scanner input = new Scanner(System.in);
        System.out.println("What do you want to do ?");
        System.out.println("<1> Get Transactions By Date");
        System.out.println("<2> Get Customer Details by ID");
        System.out.println("<3> Create New Manager");
        System.out.println("<4> Set Forex");
        System.out.println("<5> Set Stock Prices");
        System.out.println("<6> Return to Previous Menu");
        System.out.println("<q> Quit");

        String choice = input.next();

        while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3")&& !choice.equals("4") && !choice.equals("5") && !choice.equals("6") && !choice.equals("q")) {

            System.out.println();
            System.out.println("You've entered an incorrect option !");
            System.out.println();
            System.out.println("What do you want to do ?");
            System.out.println("<1> Get Transactions By Date");
            System.out.println("<2> Get Customer Details by ID");
            System.out.println("<3> Create New Manager");
            System.out.println("<4> Set Forex");
            System.out.println("<5> Set Stock Prices");
            System.out.println("<6> Return to Previous Menu");
            System.out.println("<q> Quit");
            choice = input.next();
        }

        if(choice.equals("1")){
            ManagerViewTransaction.run(manager, currency, stockMarket);
        }

        else if(choice.equals("2")){
            ManagerViewCustomer.run(manager, currency, stockMarket);
        }

        else if(choice.equals("3")){
            CreateManager.run(manager, currency, stockMarket);
        }

        else if(choice.equals("4")){
            ManagerSetForex.run(manager, currency, stockMarket);
        }

        else if(choice.equals("5")){
            ManagerSetStockPrice.run(manager, currency, stockMarket);
        }

        else if(choice.equals("6")){
            BankATM.run();
        }

        else if(choice.equals("q")){
            Printer.printExitMessage();
        }
    }

}