package com.company.Bank;

import java.io.*;
import java.util.*;

public class Parser {

    private Scanner input;

    private final String CUST_PATH = "DbFiles/Customers.csv";

    private final String MANAGER_PATH = "DbFiles/Manager.csv";

    private final String CUST_ACC_PATH = "DbFiles/CustomerAccounts.csv";

    private final String MANAGER_ACC_PATH = "DbFiles/ManagerAccounts.csv";

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

    //Customer/Manager layout: id, name, pwd, checkings_id, savings_id, loan_id, stock_id, admin_id
    public List<String> parsePersonAccountIds(String name, String pwd, boolean isCustomer) {
        String delimiter = ",";
        String record = null;
        List<String> accountIdList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(isCustomer ? CUST_PATH : MANAGER_PATH));
            while ((record = br.readLine()) != null) {
                String[] accountInfo = record.split(delimiter);
                if (accountInfo[1].equals(name) && accountInfo[2].equals(pwd)) {
                    for (int i = 3; i < accountInfo.length; i++) {
                        String accountId = accountInfo[i];
                        if (accountId != null && accountId.length() > 0) {
                            accountIdList.add(accountId);
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
        return accountIdList;
    }

    //Acount record layout: acc_id, name, pwd, accoutType, balance
    public String[] parseAccount(String id, boolean isCustomer) {
        String delimiter = ",";
        String record = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(isCustomer ? CUST_ACC_PATH : MANAGER_ACC_PATH));
            while ((record = br.readLine()) != null) {
                String[] accountInfo = record.split(delimiter);
                if (accountInfo[0].equals(id)) {
                    return accountInfo;
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return new String[0];
    }

}
