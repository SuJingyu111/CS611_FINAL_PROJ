package com.company.Transactions;

import java.time.LocalDate;


public class LoanTxn extends Transaction{

    //amount positive if get loan, negative if pay back
    public LoanTxn(String id, LocalDate timestamp, double amount, String personId) {
        super(id, timestamp, amount, personId, TxnType.LOAN);
    }

}
