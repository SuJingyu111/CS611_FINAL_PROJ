package com.company.Bank;

import com.company.Account.Account;
import com.company.Exceptions.AccountAlreadyExistException;
import com.company.Persons.Person;
import com.company.Transactions.Transaction;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.util.*;
import java.io.*;

public class Writer {

    private final String CUST_PATH = "DbFiles/Customers.csv";

    private final String MANAGER_PATH = "DbFiles/Manager.csv";

    private final String CUST_ACC_PATH = "DbFiles/CustomerAccounts.csv";

    private final String MANAGER_ACC_PATH = "DbFiles/ManagerAccounts.csv";

    private final String TXN_FILE_PATH = "DbFiles/Transactions.csv";

    public void writeForex(HashMap<String, Double> foreignExchange) throws IOException {

        String fileName = System.getProperty("user.dir") + "/src/com/company/Files/" + "Forex.csv";
        FileWriter writer = new FileWriter(fileName, false);
        StringBuilder stringBuilder = new StringBuilder();

        for(String currency : foreignExchange.keySet()){
            stringBuilder.append(currency);
            stringBuilder.append(",");
            stringBuilder.append(foreignExchange.get(currency));
            stringBuilder.append(",");
        }

        writer.write(String.valueOf(stringBuilder));

    }

    //Used when a person gets a new Account, if person does not exist, create a new record
    public void grantNewAccount(Person p, Account account, boolean isCustomer) throws IOException, AccountAlreadyExistException {
        String filePath = isCustomer ? CUST_PATH : MANAGER_PATH;
        CSVReader reader = new CSVReader(new FileReader(filePath), ',');
        List<String[]> csvBody = reader.readAll();
        boolean exist = false;
        for (int row = 0; row < csvBody.size(); row++) {
            String[] personInfo = csvBody.get(row);
            if (personInfo[1].equals(p.getName()) && personInfo[2].equals(p.getPwd())) {
                writeAccount(row, csvBody, account, isCustomer);
                exist = true;
            }
        }
        if (!exist) {
            csvBody.add(p.toString().split(","));
            writeAccount(csvBody.size() - 1, csvBody, account, isCustomer);
        }
        reader.close();

        FileWriter personWriter = new FileWriter(filePath, false);
        CSVWriter writer = new CSVWriter(personWriter, ',');
        writer.writeAll(csvBody);
        writer.flush();
        writer.close();
    }

    private void writeAccount(int row, List<String[]> csvBody, Account account, boolean isCustomer) throws IOException, AccountAlreadyExistException {
        if (csvBody.get(row)[account.getTYPE().ordinal() + 3].length() > 0) {
            throw new AccountAlreadyExistException();
        }
        csvBody.get(row)[account.getTYPE().ordinal() + 3] = account.getAccountId();
        String filePath = isCustomer ? CUST_ACC_PATH : MANAGER_ACC_PATH;
        CSVReader reader = new CSVReader(new FileReader(filePath), ',');
        List<String[]> csvBodyAcc = reader.readAll();
        csvBodyAcc.add(account.toString().split(","));
        reader.close();

        FileWriter personWriter = new FileWriter(filePath, false);
        CSVWriter writer = new CSVWriter(personWriter, ',');
        writer.writeAll(csvBodyAcc);
        writer.flush();
        writer.close();
    }

    public void writeTxn(Transaction transaction) throws IOException {
        FileWriter writer = new FileWriter(TXN_FILE_PATH, true);
        writer.write(transaction.toString());
    }
}
