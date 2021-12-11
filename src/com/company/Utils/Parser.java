package com.company.Utils;

import com.company.Account.LoanAccount;
import com.company.Factories.TxnFactory;
import com.company.Transactions.Transaction;
import com.company.Utils.FilePaths;
import com.company.Account.AccountType;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

import static com.company.Utils.FilePaths.*;

import com.opencsv.CSVReader;

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

    public String[] parsePersonInfoById(String id, boolean isCustomer) {
        try {
            String personFilePath = isCustomer ? CUST_PATH : MANAGER_PATH;
            CSVReader personReader = new CSVReader(new FileReader(personFilePath), ',');
            List<String[]> personCsvBody = personReader.readAll();
            for (String[] personInfo: personCsvBody) {
                if (personInfo[0].equals(id)) {
                    return personInfo;
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return new String[0];
    }

    //-1 if not exist, if exists, return person Id
    public int checkPresence(String name, String pwd, boolean isCustomer) {
        try {
            String personFilePath = isCustomer ? CUST_PATH : MANAGER_PATH;
            CSVReader personReader = new CSVReader(new FileReader(personFilePath), ',');
            List<String[]> personCsvBody = personReader.readAll();
            for (String[] personInfo: personCsvBody) {
                if (personInfo[1].equals(name) && personInfo[2].equals(pwd)) {
                    return Integer.parseInt(personInfo[0]);
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    //Customer/Manager layout:id, name, pwd, if_have_save, if_have_check, if_have_stock, if_have_loan, if_have_admin ("T/F")
    public Map<AccountType, Boolean> parsePersonAccountExistence(String name, String pwd, boolean isCustomer) {
        Map<AccountType, Boolean> accountExistMap = new HashMap<>();
        try {
            String personFilePath = isCustomer ? CUST_PATH : MANAGER_PATH;
            CSVReader personReader = new CSVReader(new FileReader(personFilePath), ',');
            List<String[]> personCsvBody = personReader.readAll();
            for (String[] personInfo: personCsvBody) {
                if (personInfo[1].equals(name) && personInfo[2].equals(pwd)) {
                    for (int i = 3; i < personInfo.length; i++) {
                        AccountType type = AccountType.values()[i - 3];
                        if (personInfo[i].equals("T")) {
                            accountExistMap.put(type, true);
                        }
                        else {
                            accountExistMap.put(type, false);
                        }
                    }
                    break;
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return accountExistMap;

    }

    //Account record layout (general): acc_id, name, pwd, accountType, "currency_1, balance, currency2, ....."
    public List<String[]> parseAccounts(AccountType type, String name, String pwd) {
        List<String[]> accountsInfo = new ArrayList<>();
        try {
            String filePath = FilePaths.getPathByAccountType(type);
            CSVReader accReader = new CSVReader(new FileReader(filePath), ',');
            List<String[]> accCsvBody = accReader.readAll();
            if (type == AccountType.ADMIN) {
                return accCsvBody;
            }
            for (String[] accountInfo: accCsvBody) {
                if (accountInfo[1].equals(name) && accountInfo[2].equals(pwd)) {
                    accountsInfo.add(accountInfo);
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return accountsInfo;
    }

    public Map<String, Double> parseAllStockInfo() {
        Map<String, Double> stockInfo = new HashMap<>();
        try {
            String filePath = STOCK_PATH;
            CSVReader stockReader = new CSVReader(new FileReader(filePath), ',');
            List<String[]> stockCsvBody = stockReader.readAll();
            for (String[] info: stockCsvBody) {
                stockInfo.put(info[0], Double.parseDouble(info[1]));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return stockInfo;
    }

    public List<Transaction> parseTxnByPersonId(String personId) {
        List<Transaction> txnList = new ArrayList<>();
        try {
            String filePath = TXN_FILE_PATH;
            CSVReader txnReader = new CSVReader(new FileReader(filePath), ',');
            List<String[]> txnCsvBody = txnReader.readAll();
            TxnFactory txnFactory = new TxnFactory();
            for (String[] info: txnCsvBody) {
                if (info[3].equals(personId)) {
                    txnList.add(txnFactory.produceTxn(info));
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return txnList;
    }

    public List<Transaction> parseTxnByDate(LocalDate date) {
        List<Transaction> txnList = new ArrayList<>();
        try {
            String filePath = TXN_FILE_PATH;
            CSVReader txnReader = new CSVReader(new FileReader(filePath), ',');
            List<String[]> txnCsvBody = txnReader.readAll();
            TxnFactory txnFactory = new TxnFactory();
            for (String[] info: txnCsvBody) {
                if (info[1].equals(date.toString())) {
                    txnList.add(txnFactory.produceTxn(info));
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return txnList;
    }

    public List<Transaction> parseAllTxns() {
        List<Transaction> txnList = new ArrayList<>();
        try {
            String filePath = TXN_FILE_PATH;
            CSVReader txnReader = new CSVReader(new FileReader(filePath), ',');
            List<String[]> txnCsvBody = txnReader.readAll();
            TxnFactory txnFactory = new TxnFactory();
            for (String[] info: txnCsvBody) {
                txnList.add(txnFactory.produceTxn(info));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return txnList;
    }

    public List<String[]> parseOverdueLoanAccounts(LocalDate date){
        String filePath = FilePaths.getPathByAccountType(AccountType.LOAN);
        List<String[]> overdueAccList = new ArrayList<>();
        try {
            CSVReader loanAccReader = new CSVReader(new FileReader(filePath), ',');
            List<String[]> loanAccBody = loanAccReader.readAll();
            for (String[] accInfo : loanAccBody) {
                String[] loans = accInfo[5].split(" ");
                if (loans.length >= 2) {
                    for (int i = 0; i < loans.length; i+=2) {
                        String dateStr = loans[i];
                        LocalDate localDate = LocalDate.parse(dateStr);
                        if (localDate.isBefore(date.minusMonths(1))) {
                            overdueAccList.add(accInfo);
                            break;
                        }
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return overdueAccList;
    }

    public List<String[]> parseAccountsByType(AccountType type){
        String filePath = FilePaths.getPathByAccountType(type);
        try {
            CSVReader accReader = new CSVReader(new FileReader(filePath), ',');
            return accReader.readAll();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

}
