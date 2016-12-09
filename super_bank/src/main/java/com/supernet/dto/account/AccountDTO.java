package com.supernet.dto.account;

import com.supernet.dto.BaseDTO;
import com.supernet.model.accounts.Account;

/**
 * Created by User on 05.11.2016.
 */
public class AccountDTO
        extends BaseDTO<Account> {

    private String firstName;

    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    protected Class<Account> getEntityClass() {
        return Account.class;
    }
}

