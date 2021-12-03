package com.company.Bank;

import com.company.Factories.PersonFactory;
import com.company.Persons.Customer;
import java.io.IOException;
import java.util.Scanner;

public class CustomerOptions {

    public static void run() throws IOException {

        Scanner input = new Scanner(System.in);
        Parser parser = new Parser();

        System.out.println("Please enter your name : ");
        String name  = input.next();
        System.out.println("Please enter your password : ");
        String password = input.next();

        Boolean presence = parser.checkPresence(name, password, true);

        if(!presence){

            System.out.println();
            System.out.println("You are not a customer !");
            BankATM.run();

        }

        if(presence) {

            PersonFactory personFactory = new PersonFactory();
            Customer customer = personFactory.produceCustomer(name, password);
        }

    }
}
