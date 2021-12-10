package com.company.Account;

import com.company.Bank.CurrencyType;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class LoanAccount extends Account{

    //LocalDate format: yy-mm-dd
    private Map<LocalDate, Double> amountsDue;

    public LoanAccount(String accountId, String ownerName, String pwd, AccountType type) {
        super(accountId, ownerName, pwd, type);
        this.amountsDue = new HashMap<>();
    }

    public LoanAccount(String accountId, String ownerName, String pwd, AccountType type, Map<CurrencyType, Double> balance) {
        this(accountId, ownerName, pwd, type);
        setAllBalance(balance);
        this.amountsDue = new HashMap<>();
    }

    public void getLoan(LocalDate date, Double amount) {
        amountsDue.put(date, amountsDue.getOrDefault(date, 0.0) + amount);
    }

    //Date should exist and amount should not exceed the total amount
    public void payLoan(LocalDate date, Double amount) {
        amountsDue.put(date, amountsDue.get(date) - amount);
        if (amountsDue.get(date) == 0) {
            amountsDue.remove(date);
        }
    }

    public Set<LocalDate> getAmountDueDates() {
        return amountsDue.keySet();
    }

    public Map<LocalDate, Double> getAmountsDue() {
        return amountsDue;
    }

    public void setAmountsDue(Map<LocalDate, Double> amountsDue) {
        this.amountsDue = amountsDue;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(super.toString());
        stringBuilder.append(",");
        for (Map.Entry<LocalDate, Double> ent : amountsDue.entrySet()) {
            stringBuilder.append(ent.getKey().toString()).append(" ").append(ent.getValue()).append(" ");
        }
        stringBuilder.delete(stringBuilder.length() - (amountsDue.isEmpty() ? 0 : 1), stringBuilder.length());
        return stringBuilder.toString();
    }
}
