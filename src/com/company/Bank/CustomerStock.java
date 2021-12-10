package com.company.Bank;

import com.company.Persons.Customer;
import com.company.Stock.StockMarket;
import com.company.Utils.Parser;

import java.util.*;

public class CustomerStock {

    public static void run(Customer customer, Currency currency, StockMarket stockMarket){

        Parser parser = new Parser();

        Map<String, Double> allStocks = parser.parseAllStockInfo();
        System.out.println();
        System.out.println("STOCK MARKET : ");
        System.out.println("********************************************************************************************");
        System.out.println("    Stock        Price ");
        int count = 1;
        for (Map.Entry<String, Double> entry : allStocks.entrySet()){
            System.out.println("<" + count + "> " + entry.getKey() + "         " + entry.getValue());
        }
        System.out.println("********************************************************************************************");
        System.out.println();
    }


}
