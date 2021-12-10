package com.company.Transactions;

import java.util.Date;

public class DepositOrWithdrawTxn extends Transaction{

    //Amount is positive if deposit, negative if withdraw
    public DepositOrWithdrawTxn(String id, Date timestamp, double amount, int personId) {
        super(id, timestamp, amount, personId, TxnType.DEPOSIT_OR_WITHDRAW);
    }
}
