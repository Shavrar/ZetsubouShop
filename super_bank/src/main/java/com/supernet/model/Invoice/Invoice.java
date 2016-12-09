package com.supernet.model.Invoice;

import com.supernet.model.PersistentObject;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;

/**
 * Created by User on 05.11.2016.
 */
@Entity
@Table(name = "invoices")
public class Invoice extends PersistentObject{
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

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

    private long userId;
    private long cardNumber;
    private Date expireDate;
    private String password;
    private long amount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Invoice invoice = (Invoice) o;

        if (userId != invoice.userId) return false;
        if (cardNumber != invoice.cardNumber) return false;
        if (amount != invoice.amount) return false;
        if (!expireDate.equals(invoice.expireDate)) return false;
        return password.equals(invoice.password);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (int) (cardNumber ^ (cardNumber >>> 32));
        result = 31 * result + expireDate.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + (int) (amount ^ (amount >>> 32));
        return result;
    }
}
