package com.company.Transactions;

import java.time.LocalDate;

public class TransferTxn extends Transaction{

    private String fromAccId;

    private String toPersonId;

    private String toAccId;

    public TransferTxn(String id, LocalDate timestamp, double amount, String personId, String fromAccId,
                       String toPersonId, String toAccId) {
        super(id, timestamp, amount, personId, TxnType.TRANSFER);
        setFromAccId(fromAccId);
        setToPersonId(toPersonId);
        setToPersonAccId(toAccId);
    }


    public String getFromAccId() {
        return fromAccId;
    }

    public void setFromAccId(String fromAccId) {
        this.fromAccId = fromAccId;
    }

    public String getToPersonId() {
        return toPersonId;
    }

    public void setToPersonId(String toPersonId) {
        this.toPersonId = toPersonId;
    }

    public String getToAccId() {
        return toAccId;
    }

    public void setToPersonAccId(String toAccId) {
        this.toAccId = toAccId;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(super.toString());
        stringBuilder.append(",");
        stringBuilder.append(fromAccId).append(",");
        stringBuilder.append(toPersonId).append(",");
        stringBuilder.append(toAccId);
        return stringBuilder.toString();
    }

}
