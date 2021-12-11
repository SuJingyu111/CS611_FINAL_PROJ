package com.company.Bank;

import com.company.Factories.StockFactory;
import com.company.Persons.Manager;
import com.company.Stock.Stock;
import com.company.Stock.StockMarket;
import com.company.Utils.Parser;
import com.company.Utils.Writer;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class ManagerSetStockPrice {

    public static void run(Manager manager, Currency currency, StockMarket stockMarket) throws IOException {

        Parser parser = new Parser();
        Scanner input = new Scanner(System.in);
        Writer writer = new Writer();
        StockFactory stockFactory = new StockFactory();

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

        System.out.println("What do you want to do : ");
        System.out.println("<1> Update price");
        System.out.println("<2> Delete stock");
        String choice = input.next();

        while(!choice.equals("1") && !choice.equals("2")){
            System.out.println();
            System.out.println("You've entered an incorrect input");
            System.out.println("What do you want to do : ");
            System.out.println("<1> Update price");
            System.out.println("<2> Delete stock");
            choice = input.next();
        }

        if(choice.equals("1")){

            System.out.println("Enter Name of Stock :");
            String name = input.next();
            System.out.println("Enter new price : ");
            Double price = input.nextDouble();

            Stock stock = stockFactory.produceSingleStock(name, price);
            writer.updateStock(stock, false);
        }

        else if(choice.equals("2")){

            System.out.println("Enter Name of Stock :");
            String name = input.next();

            Stock stock = stockFactory.produceSingleStock(name, 0.0);
            writer.updateStock(stock, true);
        }

        stockMarket.refresh();
        ManagerOptions.options(manager, currency, stockMarket);
    }
}
