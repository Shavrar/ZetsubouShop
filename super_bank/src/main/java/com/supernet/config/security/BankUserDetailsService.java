package com.supernet.config.security;

import com.supernet.model.accounts.Account;
import com.supernet.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service for EShop user details
 */
@Service
public class BankUserDetailsService
        implements UserDetailsService {

    @Autowired
    private AccountService accountService;

    public Long getUserIdByUsername(String username){
        return accountService.findAccountByUsername(username).getId();
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Account account = accountService.findAccountByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException(username);
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(account.getUserRole().toString()));
        return new BankUserDetails(account, grantedAuthorities);
    }
}
