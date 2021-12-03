package com.company.Factories;

import com.company.Account.Account;
import com.company.Account.AccountType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountFactory {

    public Map<AccountType, Account> produceAccountMap(List<String[]> accountInfo) {
        Map<AccountType, Account> map = new HashMap<>();
        for (String[] info : accountInfo) {
            if (info.length <= 0) {
                continue;
            }
            Account account = produceAccount(info);
            map.put(account.getTYPE(), account);
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
