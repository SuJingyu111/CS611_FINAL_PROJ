package com.company.Utils;

import com.company.Account.AccountType;

public class FilePaths {

    public static final String CUST_PATH = System.getProperty("user.dir") + "/src/com/company/Files/" + "Customers.csv";

    public static final String MANAGER_PATH = System.getProperty("user.dir") + "/src/com/company/Files/" + "Manager.csv";

    //private final String CUST_ACC_PATH = System.getProperty("user.dir") + "/src/com/company/Files/" + "CustomerAccounts.csv";
    public static final String CHECKINGS_ACC_PATH = System.getProperty("user.dir") + "/src/com/company/Files/" + "CheckingsAccounts.csv";

    public static final String SAVINGS_ACC_PATH = System.getProperty("user.dir") + "/src/com/company/Files/" + "SavingsAccounts.csv";

    public static final String STOCK_ACC_PATH = System.getProperty("user.dir") + "/src/com/company/Files/" + "StockAccounts.csv";

    public static final String LOAN_ACC_PATH = System.getProperty("user.dir") + "/src/com/company/Files/" + "LoanAccounts.csv";

    public static final String MANAGER_ACC_PATH = System.getProperty("user.dir") + "/src/com/company/Files/" + "ManagerAccounts.csv";

    public static final String STOCK_PATH = System.getProperty("user.dir") + "/src/com/company/Files/" + "Stocks.csv";

    public static final String TXN_FILE_PATH = System.getProperty("user.dir") + "/src/com/company/Files/" + "Transactions.csv";

    public static String getPathByAccountType(AccountType type) {
        switch (type){
            case SAVINGS -> {
                return SAVINGS_ACC_PATH;
            }
            case CHECKINGS -> {
                return CHECKINGS_ACC_PATH;
            }
            case LOAN -> {
                return LOAN_ACC_PATH;
            }
            case STOCK -> {
                return STOCK_ACC_PATH;
            }
            case ADMIN -> {
                return MANAGER_ACC_PATH;
            }
            default -> {
                return "";
            }
        }
    }

}
