package com.supernet.config.social;


import com.supernet.model.accounts.Account;
import com.supernet.model.accounts.Role;
import com.supernet.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UserProfile;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Create account if not exist
 */
@Service
public class ConnectionSignUpImpl
        implements ConnectionSignUp {

    @Autowired
    private AccountService accountService;

    @Override
    public String execute(Connection<?> connection) {
        UserProfile up = connection.fetchUserProfile();
        Account account = new Account();
        account.setLogin(up.getEmail());
        account.setPassword(UUID.randomUUID().toString());
        account.setUserRole(Role.ROLE_USER);
        accountService.save(account);
        return account.getLogin();
    }
}
