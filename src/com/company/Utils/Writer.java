package com.company.Utils;

import com.company.Account.Account;
import com.company.Account.AccountType;
import com.company.Stock.Stock;
import com.company.Utils.FilePaths;
import com.company.Exceptions.AccountAlreadyExistException;
import com.company.Persons.Person;
import com.company.Transactions.Transaction;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

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
            stringBuilder.append(foreignExchange.get(currency));
            stringBuilder.append(",");
        }

        writer.write(String.valueOf(stringBuilder));
        writer.flush();
        writer.close();
    }

    public void writeNewPerson(Person p, boolean isCustomer) throws IOException {
        String personFilePath = isCustomer ? FilePaths.CUST_PATH : FilePaths.MANAGER_PATH;
        CSVReader personReader = new CSVReader(new FileReader(personFilePath), ',');
        List<String[]> personCsvBody = personReader.readAll();
        personCsvBody.add(p.toString().split(","));
        personReader.close();

        writeBackToFile(personFilePath, personCsvBody);
        /*
        FileWriter personWriter = new FileWriter(personFilePath, false);
        CSVWriter writer = new CSVWriter(personWriter, ',');
        writer.writeAll(personCsvBody);
        writer.flush();
        writer.close();

         */
    }

    //Used when a person gets a new Account, if person does not exist, create a new record
    public void grantNewAccount(Person p, Account account, boolean isCustomer) throws IOException, AccountAlreadyExistException {
        String accountFilePath = FilePaths.getPathByAccountType(account.getTYPE());
        String personFilePath = isCustomer ? FilePaths.CUST_PATH : FilePaths.MANAGER_PATH;
        CSVReader personReader = new CSVReader(new FileReader(personFilePath), ',');
        List<String[]> personCsvBody = personReader.readAll();
        boolean exist = false;
        for (int row = 0; row < personCsvBody.size(); row++) {
            String[] personInfo = personCsvBody.get(row);
            if (personInfo[1].equals(p.getName()) && personInfo[2].equals(p.getPwd())) {
                writeAccount(row, personCsvBody, account, accountFilePath);
                exist = true;
            }
        }
        if (!exist) {
            personCsvBody.add(p.toString().split(","));
            writeAccount(personCsvBody.size() - 1, personCsvBody, account, accountFilePath);
        }
        personReader.close();

        writeBackToFile(personFilePath, personCsvBody);
        /*
        FileWriter personWriter = new FileWriter(personFilePath, false);
        CSVWriter writer = new CSVWriter(personWriter, ',');
        writer.writeAll(personCsvBody);
        writer.flush();
        writer.close();

         */
    }

    private void writeAccount(int row, List<String[]> personCsvBody, Account account, String accountFilePath) throws IOException, AccountAlreadyExistException {
        //csvBody.get(row)[account.getTYPE().ordinal() + 3] = account.getAccountId();
        //String filePath = isCustomer ? CUST_ACC_PATH : MANAGER_ACC_PATH;
        writeAccountToPath(account, accountFilePath);
        personCsvBody.get(row)[account.getTYPE().ordinal() + 3] = "T";
    }

    public void writeTxn(Transaction transaction) throws IOException {
        /*
        CSVReader txnReader = new CSVReader(new FileReader(FilePaths.TXN_FILE_PATH), ',');
        List<String[]> csvBodyAcc = txnReader.readAll();
        //csvBodyAcc.add(account.toString().split(","));
        csvBodyAcc.add(transaction.toString().split(","));
        txnReader.close();

        FileWriter accWriter = new FileWriter(FilePaths.TXN_FILE_PATH, false);
        CSVWriter writer = new CSVWriter(accWriter, ',');
        writer.writeAll(csvBodyAcc);
        writer.flush();
        writer.close();
        */

        //System.out.println("!!!!!!!!!");
        CSVWriter csvWriter = new CSVWriter(new FileWriter(FilePaths.TXN_FILE_PATH, true));
        csvWriter.writeNext(transaction.toString().split(","));
        csvWriter.flush();
        csvWriter.close();
        //FileWriter writer = new FileWriter(FilePaths.TXN_FILE_PATH, true);
        //writer.write(transaction.toString();
    }

    public void updateAccountToDisk(Account account) throws IOException {
        String filePath = FilePaths.getPathByAccountType(account.getTYPE());
        writeAccountToPath(account, filePath);
    }

    private void writeAccountToPath(Account account, String filePath) throws IOException {
        CSVReader accountReader = new CSVReader(new FileReader(filePath), ',');
        List<String[]> csvBodyAcc = accountReader.readAll();
        //csvBodyAcc.add(account.toString().split(","));
        boolean exist = false;
        for (int i = 0; i < csvBodyAcc.size(); i++) {
            String[] accountInfo = csvBodyAcc.get(i);
            if (accountInfo[0].equals(account.getAccountId())) {
                csvBodyAcc.set(i, account.toString().split(","));
                exist = true;
                break;
            }
        }
        if (!exist) {
            csvBodyAcc.add(account.toString().split(","));
        }
        accountReader.close();

        writeBackToFile(filePath, csvBodyAcc);
    }

    public void deleteAccount(AccountType type, String id) throws IOException {
        String filePath = FilePaths.getPathByAccountType(type);
        CSVReader accountReader = new CSVReader(new FileReader(filePath), ',');
        List<String[]> csvBodyAcc = accountReader.readAll();
        int deleteIdx = -1;
        for (int i = 0; i < csvBodyAcc.size(); i++) {
            String[] accountInfo = csvBodyAcc.get(i);
            if (accountInfo[0].equals(id)) {
                deleteIdx = i;
                break;
            }
        }
        if (deleteIdx >= 0) {
            csvBodyAcc.remove(deleteIdx);
        }
        writeBackToFile(filePath, csvBodyAcc);
    }

    private void writeBackToFile(String filePath, List<String[]> csvBodyAcc) throws IOException {
        FileWriter accWriter = new FileWriter(filePath, false);
        CSVWriter writer = new CSVWriter(accWriter, ',');
        writer.writeAll(csvBodyAcc);
        writer.flush();
        writer.close();
    }

    //update existing stock, if the stock does not already exist, add to the list
    public void updateStock(Stock stock, boolean ifDelete) throws IOException {
        String filePath = FilePaths.STOCK_PATH;
        CSVReader stockReader = new CSVReader(new FileReader(filePath), ',');
        List<String[]> csvBodyStock = stockReader.readAll();
        int deleteIdx = -1;
        for (int i = 0; i < csvBodyStock.size(); i++) {
            String[] stockInfo = csvBodyStock.get(i);
            if (stockInfo[0].equals(stock.getCorpName())) {
                if (!ifDelete) {
                    stockInfo[1] = String.valueOf(stock.getPrice());
                }
                deleteIdx = i;
                break;
            }
        }
        if (ifDelete && deleteIdx >= 0) {
            csvBodyStock.remove(deleteIdx);
        }
        if (!ifDelete && deleteIdx < 0) {
            csvBodyStock.add(stock.toString().split(","));
        }
        writeBackToFile(FilePaths.STOCK_PATH, csvBodyStock);
    }
}
