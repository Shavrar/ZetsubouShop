package com.supernet.reposotories.account;

import com.querydsl.core.BooleanBuilder;
import com.supernet.dto.account.AccountDTO;
import com.supernet.filters.account.AccountFilter;
import com.supernet.model.accounts.Account;
import com.supernet.model.accounts.QAccount;
import com.supernet.reposotories.BaseRepositoryImpl;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * Account repository impl
 */
public class AccountRepositoryImpl
        extends BaseRepositoryImpl<Account, AccountFilter>
        implements AccountRepositoryCustom {

    /**
     * Base constructor
     */
    public AccountRepositoryImpl() {
        super(Account.class);
    }

    @Transactional
    @Override
    public void update2fills(AccountDTO accountDTO) {
        QAccount account = QAccount.account;
        update(account)
                .set(account.firstName, accountDTO.getFirstName())
                .set(account.lastName, accountDTO.getLastName()).execute();
    }

    @Override
    protected BooleanBuilder getQueryPredicate(AccountFilter searchFilter) {
        QAccount account = QAccount.account;
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        Optional.ofNullable(searchFilter.getQuery())
                .ifPresent(query -> booleanBuilder.and(account.login.eq(searchFilter.getQuery())));

        return booleanBuilder;
    }
}
