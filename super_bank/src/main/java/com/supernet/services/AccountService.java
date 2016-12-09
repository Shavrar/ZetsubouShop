package com.supernet.services;

import com.supernet.dto.account.AccountDTO;
import com.supernet.filters.account.AccountFilter;
import com.supernet.model.accounts.Account;
import com.supernet.reposotories.BaseRepositoryCustom;
import com.supernet.reposotories.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


/**
 * Service for account
 */
@Service
public class AccountService
        implements BaseCRUDService<Account, AccountFilter> {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public JpaRepository<Account, Long> getRepository() {
        return accountRepository;
    }

    @Override
    public BaseRepositoryCustom<Account, AccountFilter> getRepositoryCustom() {
        return accountRepository;
    }

    /**
     * Find Account by username
     *
     * @param username username
     * @return Account
     */
    public Account findAccountByUsername(String username) {
        return accountRepository.findAccountByLogin(username);
    }

    private String getCurrentAccountUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public void udate2fills(AccountDTO accountDTO) {
        accountRepository.update2fills(accountDTO);
    }
}
