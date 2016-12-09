package com.supernet.model.Transaction;

import com.supernet.model.PersistentObject;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;

/**
 * Created by User on 04.12.2016.
 */
@Entity
@Table(name = "transactions")
public class Transaction extends PersistentObject {
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getAmount() {
        return Amount;
    }

    public void setAmount(long amount) {
        Amount = amount;
    }

    public long getSenderAccountId() {
        return SenderAccountId;
    }

    public void setSenderAccountId(long senderAccountId) {
        SenderAccountId = senderAccountId;
    }

    public long getReciverAccountId() {
        return ReciverAccountId;
    }

    public void setReciverAccountId(long reciverAccountId) {
        ReciverAccountId = reciverAccountId;
    }

    private Date date;
    private long Amount;
    private long SenderAccountId;
    private long ReciverAccountId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Transaction that = (Transaction) o;

        if (Amount != that.Amount) return false;
        if (SenderAccountId != that.SenderAccountId) return false;
        if (ReciverAccountId != that.ReciverAccountId) return false;
        return date.equals(that.date);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + (int) (Amount ^ (Amount >>> 32));
        result = 31 * result + (int) (SenderAccountId ^ (SenderAccountId >>> 32));
        result = 31 * result + (int) (ReciverAccountId ^ (ReciverAccountId >>> 32));
        return result;
    }
}
