package com.supernet.services;

import com.supernet.filters.transaction.TransactionFilter;
import com.supernet.model.Transaction.Transaction;
import com.supernet.reposotories.BaseRepositoryCustom;
import com.supernet.reposotories.transaction.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


/**
 * Created by User on 04.12.2016.
 */
@Service
public class TransactionService implements BaseCRUDService<Transaction, TransactionFilter> {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public JpaRepository<Transaction, Long> getRepository() {
        return transactionRepository;
    }

    @Override
    public BaseRepositoryCustom<Transaction, TransactionFilter> getRepositoryCustom() {
        return transactionRepository;
    }

}