package com.company.Bank;


import com.company.Stock.StockMarket;
import com.company.Utils.Printer;

import java.io.*;
import java.util.*;

public class BankATM {

    public static void run() throws IOException {

        Currency currency = new Currency();
        StockMarket stockMarket = StockMarket.getInstance();

        Scanner in = new Scanner(System.in);

        Printer.printWelcomeMessage();

        System.out.println("Please choose an option :");
        System.out.println("I'm a  <1> Customer ");
        System.out.println("       <2> Manager");
        System.out.println("       <3> New Customer");
        System.out.println("       <q> Quit");
        String choice = in.next();

        while(!choice.equals("1") && !choice.equals("2") && !choice.equals("3") && !choice.equals("q")){
            System.out.println("You've entered an incorrect option !");
            System.out.println("Please choose again :");
            System.out.println("I'm a  <1> Customer ");
            System.out.println("       <2> Manager");
            System.out.println("       <3> New Customer");
            System.out.println("       <q> Quit");
            choice = in.next();
        }

        if(choice.equals("1")){
            CustomerOptions.run(currency, stockMarket);
        }

        else if(choice.equals("2")){
            ManagerOptions.run(currency);
        }

        else if(choice.equals("3")){
            CreateCustomer.run(currency, stockMarket);
        }

        else{
            Printer.printExitMessage();
        }
    }
}
