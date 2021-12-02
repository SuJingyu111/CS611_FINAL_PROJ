package com.company;

import java.io.*;
import java.util.*;

public class Parser {

    private Scanner input;

    public HashMap<String, Double> parseForex() throws FileNotFoundException{

        HashMap<String, Double> foreignExchange = new HashMap<>();
        String fileName = System.getProperty("user.dir") + "/src/com/company/Files/" + "Forex.csv";
        input = new Scanner(new File(fileName));
        input.useDelimiter(",");
        while (input.hasNext()){
            foreignExchange.put(input.next(), Double.parseDouble(input.next()));
        }

        return foreignExchange;

    }
}
