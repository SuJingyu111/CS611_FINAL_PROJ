package com.company.Bank.ManagerAllOptions;

import com.company.Currency.Currency;
import com.company.Persons.Manager;
import com.company.Stock.StockMarket;

import java.io.IOException;

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

        

        ManagerOptions.options(manager, currency, stockMarket);

    }
}
