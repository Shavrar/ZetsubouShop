package com.supernet.dto.invoice;

import java.sql.Date;

/**
 * Created by User on 05.12.2016.
 */
public class InvoiceDTO {

    private long cardNumber;
    private Date expireDate;
    private String password;
    private long amount;


    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }


}
