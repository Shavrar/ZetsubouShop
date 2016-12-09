package com.supernet.dto.account;

import com.supernet.dto.BaseDTO;
import com.supernet.model.accounts.Account;
import com.supernet.model.accounts.Role;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class AccountViewDTO
        extends BaseDTO<Account> {

    private String firstName;

    private String lastName;

    private String login;

    private String password;

    private Role userRole;

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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getUserRole() {
        return userRole;
    }

    public void setUserRole(Role userRole) {
        this.userRole = userRole;
    }

    @Override
    protected Class<Account> getEntityClass() {
        return Account.class;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof AccountViewDTO)) {
            return false;
        }

        AccountViewDTO that = (AccountViewDTO) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(getFirstName(), that.getFirstName())
                .append(getLastName(), that.getLastName())
                .append(getLogin(), that.getLogin())
                .append(getPassword(), that.getPassword())
                .append(getUserRole(), that.getUserRole())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getLastName())
                .append(getFirstName())
                .append(getLogin())
                .append(getPassword())
                .append(getUserRole())
                .toHashCode();
    }
}
