package com.supernet.dto.invoice;

import com.supernet.dto.BaseDTO;
import com.supernet.model.Invoice.Invoice;

import java.sql.Date;

/**
 * Created by User on 04.12.2016.
 */
public class InvoiceViewDTO extends BaseDTO<Invoice> {

    private long userId;
    private long cardNumber;
    private Date expireDate;
    private String password;
    private long amount;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        InvoiceViewDTO that = (InvoiceViewDTO) o;

        if (userId != that.userId) return false;
        if (cardNumber != that.cardNumber) return false;
        if (amount != that.amount) return false;
        if (expireDate != null ? !expireDate.equals(that.expireDate) : that.expireDate != null) return false;
        return password != null ? password.equals(that.password) : that.password == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (int) (cardNumber ^ (cardNumber >>> 32));
        result = 31 * result + (expireDate != null ? expireDate.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (int) (amount ^ (amount >>> 32));
        return result;
    }

    @Override
    protected Class<Invoice> getEntityClass() {
        return Invoice.class;
    }
}
