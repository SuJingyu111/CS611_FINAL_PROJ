package com.company;

import com.company.Account.Account;

import java.util.*;
import java.io.*;

public class Writer {

    private final String CUST_PATH = "DbFiles/Customers.csv";

    private final String MANAGER_PATH = "DbFiles/Manager.csv";

    private final String CUST_ACC_PATH = "DbFiles/CustomerAccounts.csv";

    private final String MANAGER_ACC_PATH = "DbFiles/ManagerAccounts.csv";

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

    public void writeAccount(Account account) throws IOException {
        FileWriter writer = new FileWriter(CUST_ACC_PATH, false);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(account.getAccountId()).append(",");
        stringBuilder.append(account.getOwnerName()).append(",");
        stringBuilder.append(account.getPwd()).append(",");
        stringBuilder.append(account.getTYPE()).append(",");
        stringBuilder.append(account.getBalance());
        writer.write(stringBuilder.toString());
    }

}
