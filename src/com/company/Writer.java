package com.company;

import java.util.*;
import java.io.*;

public class Writer {

    public void writeForex(HashMap<String, Double> foreignExchange) throws IOException {

        String fileName = System.getProperty("user.dir") + "/src/com/company/Files/" + "Forex.csv";
        FileWriter writer = new FileWriter(fileName, false);
        StringBuilder stringBuilder = new StringBuilder();

        for(String currency : foreignExchange.keySet()){
            stringBuilder.append(currency);
            stringBuilder.append(",");
            stringBuilder.append(String.valueOf(foreignExchange.get(currency)));
            stringBuilder.append(",");
        }

        writer.write(String.valueOf(stringBuilder));

    }
}
