package com.company.Bank.ManagerAllOptions;

import com.company.Currency.Currency;
import com.company.Factories.PersonFactory;
import com.company.Persons.Customer;
import com.company.Persons.Manager;
import com.company.Stock.StockMarket;

import java.io.IOException;
import java.util.List;

/**
 * Class responsible for allowing managers to view all debtors
 */

public class ManagerViewAllDebtors {

    /**
     * Method responsible for allowing managers to view all debtors
     * @param currency Holds Forex info for the day
     * @param stockMarket Holds stock info for the day
     * @throws IOException Ensures proper file parsing
     */
    public static void run(Manager manager, Currency currency, StockMarket stockMarket) throws IOException{

        PersonFactory personFactory = new PersonFactory();
        List<Customer> debtors = personFactory.

        ManagerOptions.options(manager, currency, stockMarket);

    }
}
