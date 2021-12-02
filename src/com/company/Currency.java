package com.company;

import java.io.FileNotFoundException;
import java.util.*;

public class Currency {

    private HashMap<String, Double> foreignExchange = new HashMap<>();
    private Parser parser = new Parser();

    public Currency() throws FileNotFoundException {

        foreignExchange = parser.parseForex();
    }

    public void setForex(String currency, Double value ){

        foreignExchange.remove(currency);
        foreignExchange.put(currency, value);
    }

    public Double getForex(String currency){

        return foreignExchange.get(currency);
    }

}
