package com.company.Factories;

import com.company.Account.*;
import com.company.Bank.CurrencyType;
import com.company.Exceptions.AccountNotExistException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountFactory {

    public Map<AccountType, List<Account>> produceAccountMap(Map<AccountType, List<String[]>> accountInfoMap) {
        Map<AccountType, List<Account>> map = new HashMap<>();
        for (AccountType type : accountInfoMap.keySet()) {
            List<String[]> infoList = accountInfoMap.get(type);
            List<Account> accountList = new ArrayList<>();
            for (String[] info : infoList) {
                accountList.add(produceAccount(info));
            }
            map.put(type, accountList);
        }
        return map;
    }

    public Account produceAccount(String[] args) {
        String id = args[0], name = args[1], pwd = args[2];
        AccountType type = AccountType.valueOf(args[3]);
        String[] balance = args[4].split(" ");
        for (String arg : balance) {
            System.out.println(arg);
        }
        //int balance = Integer.parseInt(args[4]);
        /*
        Account account = new Account(id, name, pwd, type);
        for (int i = 4; i < args.length; i+=2) {
            account.addToBalance(CurrencyType.valueOf(args[i]), Double.parseDouble(args[i + 1]));
        }
        return account;
         */
        switch (type) {
            case SAVINGS -> {
                SavingsAccount account = new SavingsAccount(id, name, pwd, type);
                setUpBalance(account, balance);
                return account;
            }
            case CHECKINGS -> {
                CheckingsAccount account = new CheckingsAccount(id, name, pwd, type);
                setUpBalance(account, balance);
                return account;
            }
            case ADMIN -> {
                AdminAccount account = new AdminAccount(id, name, pwd, type);
                setUpBalance(account, balance);
                return account;
            }
            case LOAN -> {
                LoanAccount account = new LoanAccount(id, name, pwd, type);
                setUpBalance(account, balance);
                String[] amountsDueInfo = args[5].split(" ");
                System.out.println(args[5]);
                setUpAmountsDue(account, amountsDueInfo);
                return account;
            }
            case STOCK -> {
                StockAccount account = new StockAccount(id, name, pwd, type);
                setUpBalance(account, balance);
                String[] sharesHoldingInfo = args[5].split(" ");
                setUpSharesHolding(account, sharesHoldingInfo);
                return account;
            }
            default -> {
                throw new AccountNotExistException();
            }
        }
    }

    private void setUpBalance(Account account, String[] balance) {
        if (balance.length < 2) {
            return;
        }
        for (int i = 0; i < balance.length; i+=2) {
            System.out.println(balance[i]);
            account.addToBalance(CurrencyType.valueOf(balance[i]), Double.parseDouble(balance[i + 1]));
        }
    }

    private void setUpAmountsDue(LoanAccount account, String[] amountsDueInfo) {
        if (amountsDueInfo.length < 2) {
            return;
        }
        for (int i = 0; i < amountsDueInfo.length; i+=2) {
            String[] dateInfo = amountsDueInfo[i].split("-");
            int year = Integer.parseInt(dateInfo[0]), month = Integer.parseInt(dateInfo[1]), day = Integer.parseInt(dateInfo[2]);
            LocalDate date = LocalDate.of(year, month, day);
            account.getLoan(date, Double.parseDouble(amountsDueInfo[i + 1]));
        }
    }

    private void setUpSharesHolding(StockAccount account, String[] sharesHoldingInfo) {
        if (sharesHoldingInfo.length < 2) {
            return;
        }
        for (int i = 0; i < sharesHoldingInfo.length; i+=2) {
            account.addShare(sharesHoldingInfo[i], Integer.parseInt(sharesHoldingInfo[i + 1]));
        }
    }

}
