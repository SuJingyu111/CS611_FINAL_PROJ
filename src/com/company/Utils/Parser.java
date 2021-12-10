package com.company.Utils;

import com.company.Utils.FilePaths;
import com.company.Account.AccountType;

import java.io.*;
import java.util.*;

import static com.company.Utils.FilePaths.*;

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

    //-1 if not exist, if exists, return person Id
    public int checkPresence(String name, String pwd, boolean isCustomer){
        String delimiter = ",";
        String record = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(isCustomer ? CUST_PATH : MANAGER_PATH));
            while ((record = br.readLine()) != null) {
                String[] accountInfo = record.split(delimiter);
                if (accountInfo[1].equals(name) && accountInfo[2].equals(pwd)) {
                    return Integer.parseInt(accountInfo[0]);
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
        String delimiter = ",";
        String record = null;
        Map<AccountType, Boolean> accountExistMap = new HashMap<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(isCustomer ? CUST_PATH : MANAGER_PATH));
            while ((record = br.readLine()) != null) {
                String[] accountInfo = record.split(delimiter);
                if (accountInfo[1].equals(name) && accountInfo[2].equals(pwd)) {
                    for (int i = 3; i < accountInfo.length; i++) {
                        AccountType type = AccountType.values()[i - 3];
                        if (accountInfo[i].equals("T")) {
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
            return null;
        }
        return accountExistMap;
    }

    //Account record layout (general): acc_id, name, pwd, accountType, currency_1, balance, currency2, .....
    public List<String[]> parseAccounts(AccountType type, String name, String pwd) {
        String delimiter = ",";
        String record = null;
        List<String[]> accountsInfo = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(FilePaths.getPathByAccountType(type)));
            while ((record = br.readLine()) != null) {
                String[] accountInfo = record.split(delimiter);
                if (accountInfo[1].equals(name) && accountInfo[2].equals(pwd)) {
                    accountsInfo.add(accountInfo);
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return accountsInfo;
    }

    /*
    public List<String[]> parseAllAcountInfo(List<String> accountIdList, boolean isCustomer) {
        List<String[]> accountInfo = new ArrayList<>();
        for (String id : accountIdList) {
            accountInfo.add(parseAccount(id, isCustomer));
        }
        return accountInfo;
    }
    */

    public Map<String, Double> parseAllStockInfo() {
        Map<String, Double> stockInfo = new HashMap<>();
        String delimiter = ",";
        String record = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(STOCK_PATH));
            while ((record = br.readLine()) != null) {
                String[] info = record.split(delimiter);
                stockInfo.put(info[0], Double.parseDouble(info[1]));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
        return stockInfo;
    }

}
