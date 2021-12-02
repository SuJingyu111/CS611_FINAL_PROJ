package com.company;

import java.io.*;
import java.util.*;

public class Currency {

    private HashMap<String, Double> foreignExchange = new HashMap<>();
    private Parser parser = new Parser();
    private Writer writer = new Writer();

    public Currency() throws FileNotFoundException {

        foreignExchange = parser.parseForex();
    }

    public void setForex(String currency, Double value ) throws IOException {

        foreignExchange.remove(currency);
        foreignExchange.put(currency, value);
        writer.writeForex(foreignExchange);
    }

    public Double getForex(String currency){

        return foreignExchange.get(currency);
    }

    public void display(){

        System.out.println("********************************************************************************************");
        System.out.println("    Currency    Exchange Rate");
        int count = 1;
        for (String currency : foreignExchange.keySet()) {
            System.out.println("<" + count + ">   " + currency + "        " + foreignExchange.get(currency));
            count += 1;
        }
        System.out.println("********************************************************************************************");
        System.out.println();
    }

}
