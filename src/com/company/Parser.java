package com.company;

import java.io.*;
import java.util.*;

public class Parser {

    private Scanner input;

    public HashMap<String, Double> parseForex() throws IOException{

        HashMap<String, Double> foreignExchange = new HashMap<>();
        String fileName = System.getProperty("user.dir") + "/src/com/company/Files/" + "Forex.csv";
        input = new Scanner(new File(fileName));
        input.useDelimiter(",");
        while (input.hasNext()){
            foreignExchange.put(input.next(), Double.parseDouble(input.next()));
        }

        return foreignExchange;

    }

    //Acount record layout: id, name, pwd, accoutType, balance
    public List<String[]> parseAccount(String name, String pwd, String filePath) {
        String delimiter = ",";
        String record = null;
        List<String[]> accountInfoList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            while ((record = br.readLine()) != null) {
                String[] accountInfo = record.split(delimiter);
                if (accountInfo[1].equals(name) && accountInfo[2].equals(pwd)) {
                    accountInfoList.add(accountInfo);
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return accountInfoList;
    }

}
