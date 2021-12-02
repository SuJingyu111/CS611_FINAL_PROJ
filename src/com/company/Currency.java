package com.company;

import java.util.*;

public class Currency {

    private HashMap<String, Double> foreignExchange = new HashMap<>();

    public Currency(){

        foreignExchange.put("EUR", 1.13);
        foreignExchange.put("CAD", 0.78);
        foreignExchange.put("JPY", 0.0088);
    }

    public void setForex(String currency, Double value ){

        foreignExchange.remove(currency);
        foreignExchange.put(currency, value);
    }

    public Double getForex(String currency){

        return foreignExchange.get(currency);
    }

}
