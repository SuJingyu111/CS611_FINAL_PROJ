package com.company.Factories;

import com.company.Account.Account;
import com.company.Account.AccountType;

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
        int balance = Integer.parseInt(args[4]);
        return new Account(id, name, pwd, type, balance);
    }

}
