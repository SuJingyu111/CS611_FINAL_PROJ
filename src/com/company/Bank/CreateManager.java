package com.company.Bank;
import com.company.Factories.PersonFactory;
import com.company.Persons.Customer;
import com.company.Persons.Manager;
import com.company.Utils.Writer;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class CreateManager {

    public static void run(Manager manager,Currency currency) throws IOException {

        Scanner input = new Scanner(System.in);

        System.out.println();
        System.out.println("Enter name of Account holder : ");
        String name = input.next();
        System.out.println("Enter password : ");
        String password = input.next();

        String managerId = getRandomNumberString();

        //TODO : Write manager to csv file.
        PersonFactory personFactory = new PersonFactory();
        Manager newManager = personFactory.produceNewManager(managerId, name, password);
        Writer writer = new Writer();
        writer.writeNewPerson(newManager, false);
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
