package com.company.Bank;

import com.company.Persons.Customer;
import com.company.Persons.Manager;

import java.io.IOException;
import java.util.*;

public class ManagerViewCustomer {

    public static void run(Manager manager, Currency currency) throws IOException {

        Scanner input = new Scanner(System.in);

        System.out.println();
        System.out.println("Enter Customer ID :");
        String ID = input.next();



        ManagerOptions.options(manager, currency);

    }
}
