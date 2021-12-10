package com.company.Bank;

import com.company.Factories.PersonFactory;
import com.company.Persons.Customer;
import com.company.Utils.Writer;

import java.io.IOException;
import java.util.*;

public class CreateCustomer {

    public static void run() throws IOException {

        Scanner input = new Scanner(System.in);

        System.out.println();
        System.out.println("Enter name of Account holder : ");
        String name = input.next();
        System.out.println("Enter password : ");
        String password = input.next();

        String customerId = getRandomNumberString();

        //TODO : Write customer to csv file.
        PersonFactory personFactory = new PersonFactory();
        Customer customer = personFactory.produceNewCustomer(customerId, name, password);
        Writer writer = new Writer();
        writer.writeNewPerson(customer, true);
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
