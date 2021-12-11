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

/**
 * Utils class that contains methods to write to data files.
 */
public class Writer {

    /**
     * Write to foreign currency file
     *
     * @param foreignExchange Map from currency to exchange rate
     * @throws IOException If the currency data file does not exist
     */
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

    /**
     * Writes a person to the file.
     *
     * @param p Object of person
     * @param isCustomer If the person is a customer
     * @throws IOException If person data file does not exist
     */
    public void writeNewPerson(Person p, boolean isCustomer) throws IOException {
        String personFilePath = isCustomer ? FilePaths.CUST_PATH : FilePaths.MANAGER_PATH;
        CSVReader personReader = new CSVReader(new FileReader(personFilePath), ',');
        List<String[]> personCsvBody = personReader.readAll();
        checkCSVBody(personCsvBody);
        personCsvBody.add(p.toString().split(","));
        personReader.close();

        writeBackToFile(personFilePath, personCsvBody);
    }

    /**
     * Used when a person gets a new Account, if person does not exist, create a new record.
     *
     * @param p Person Object
     * @param account Instance of the new account
     * @param isCustomer If the person is a customer
     * @throws IOException If any data file does not exist
     * @throws AccountAlreadyExistException Taking an existing account as a new one
     */
    public void grantNewAccount(Person p, Account account, boolean isCustomer) throws IOException, AccountAlreadyExistException {
        String accountFilePath = FilePaths.getPathByAccountType(account.getTYPE());
        String personFilePath = isCustomer ? FilePaths.CUST_PATH : FilePaths.MANAGER_PATH;
        CSVReader personReader = new CSVReader(new FileReader(personFilePath), ',');
        List<String[]> personCsvBody = personReader.readAll();
        checkCSVBody(personCsvBody);
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
    }

    /**
     * Marks in person record that he has a specific type of account.
     *
     * @param row Row in which to insert the new record
     * @param personCsvBody List of array of strings containing person info
     * @param account The account to add to the person
     * @param accountFilePath File path that stores info of that type of account
     * @throws IOException When some data files does not exist
     * @throws AccountAlreadyExistException Trying to give an existing account to a person
     */
    private void writeAccount(int row, List<String[]> personCsvBody, Account account, String accountFilePath) throws IOException, AccountAlreadyExistException {
        writeAccountToPath(account, accountFilePath);
        personCsvBody.get(row)[account.getTYPE().ordinal() + 3] = "T";
    }

    /**
     * Writes a
     * @param transaction
     * @throws IOException
     */
    public void writeTxn(Transaction transaction) throws IOException {
        CSVWriter csvWriter = new CSVWriter(new FileWriter(FilePaths.TXN_FILE_PATH, true));
        csvWriter.writeNext(transaction.toString().split(","));
        csvWriter.flush();
        csvWriter.close();
    }

    public void updateAccountToDisk(Account account) throws IOException {
        String filePath = FilePaths.getPathByAccountType(account.getTYPE());
        writeAccountToPath(account, filePath);
    }

    private void writeAccountToPath(Account account, String filePath) throws IOException {
        CSVReader accountReader = new CSVReader(new FileReader(filePath), ',');
        List<String[]> csvBodyAcc = accountReader.readAll();
        //csvBodyAcc.add(account.toString().split(","));
        checkCSVBody(csvBodyAcc);
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
        checkCSVBody(csvBodyAcc);
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
        checkCSVBody(csvBodyAcc);
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
        checkCSVBody(csvBodyStock);
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

    private void checkCSVBody(List<String[]> csvBody) {
        if (csvBody.size() == 1 && (csvBody.get(0).length == 0 || csvBody.get(0)[0].length() == 0)) {
            csvBody.clear();
        }
    }
}
