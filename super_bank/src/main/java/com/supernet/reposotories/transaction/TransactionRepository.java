package com.supernet.reposotories.transaction;

import com.supernet.filters.transaction.TransactionFilter;
import com.supernet.model.Transaction.Transaction;
import com.supernet.reposotories.BaseRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

/**
 * Created by User on 04.12.2016.
 */
public interface TransactionRepository extends JpaRepository<Transaction,Long>,
        QueryDslPredicateExecutor<Transaction>, TransactionRepositoryCustom {

}

interface TransactionRepositoryCustom extends BaseRepositoryCustom<Transaction, TransactionFilter> {

}
