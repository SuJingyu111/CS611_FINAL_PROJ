package com.company.Transactions;

import java.util.Date;

public class LoanTxn extends Transaction{

    //amount positive if get loan, negative if pay back
    public LoanTxn(String id, Date timestamp, double amount, int personId) {
        super(id, timestamp, amount, personId, TxnType.LOAN);
    }

}
