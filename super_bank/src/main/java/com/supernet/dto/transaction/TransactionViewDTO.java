package com.supernet.dto.transaction;

import com.supernet.dto.BaseDTO;
import com.supernet.model.Transaction.Transaction;

/**
 * Created by User on 05.12.2016.
 */
public class TransactionViewDTO extends BaseDTO<Transaction> {


    @Override
    protected Class<Transaction> getEntityClass() {
        return Transaction.class;
    }
}
