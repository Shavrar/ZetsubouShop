package com.supernet.reposotories.transaction;

import com.querydsl.core.BooleanBuilder;
import com.supernet.filters.transaction.TransactionFilter;
import com.supernet.model.Transaction.Transaction;
import com.supernet.reposotories.BaseRepositoryImpl;


/**
 * Created by User on 04.12.2016.
 */
public class TransactionRepositoryImpl extends BaseRepositoryImpl<Transaction, TransactionFilter>
        implements TransactionRepositoryCustom {
    public TransactionRepositoryImpl() {
        super(Transaction.class);
    }

    @Override
    protected BooleanBuilder getQueryPredicate(TransactionFilter searchFilter) {
        //QInvoice invoice = QInvoice.invoice;
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        return booleanBuilder;
    }
}