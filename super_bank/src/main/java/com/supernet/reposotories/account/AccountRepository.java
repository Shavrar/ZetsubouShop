package com.supernet.reposotories.account;

import com.supernet.dto.account.AccountDTO;
import com.supernet.filters.account.AccountFilter;
import com.supernet.model.accounts.Account;
import com.supernet.reposotories.BaseRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

/**
 * Repository for Account
 */
public interface AccountRepository
        extends JpaRepository<Account, Long>,
        QueryDslPredicateExecutor<Account>,
        AccountRepositoryCustom {

    /**
     * Find Account by username
     *
     * @param name username
     * @return Account
     */
    Account findAccountByLogin(String name);
}

interface AccountRepositoryCustom
        extends BaseRepositoryCustom<Account, AccountFilter> {

    void update2fills(AccountDTO accountDTO);
}
