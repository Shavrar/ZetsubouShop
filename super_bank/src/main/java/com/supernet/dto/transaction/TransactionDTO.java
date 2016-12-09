package com.supernet.dto.transaction;

/**
 * Created by User on 08.12.2016.
 */
public class TransactionDTO {
    private long amount;
    private long reciverCardNumber;
    private long senderCardNumber;
    private String password;

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getReciverCardNumber() {
        return reciverCardNumber;
    }

    public void setReciverCardNumber(long reciverCardNumber) {
        this.reciverCardNumber = reciverCardNumber;
    }

    public long getSenderCardNumber() {
        return senderCardNumber;
    }

    public void setSenderCardNumber(long senderCardNumber) {
        this.senderCardNumber = senderCardNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
