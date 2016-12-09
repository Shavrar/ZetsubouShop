/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.supernet.model.accounts;

import com.supernet.model.PersistentObject;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

/**
 * User account
 */
@Entity
@Table(name = "accounts")
public class Account
        extends PersistentObject {

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    private String firstName;

    private String lastName;

    private String login;

    private String password;


    @Enumerated(EnumType.STRING)
    private Role userRole;

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
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (this == o) {
            return true;
        }

        if (Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }

        Account account = (Account) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(getFirstName(), account.getFirstName())
                .append(getLastName(), account.getLastName())
                .append(getLogin(), account.getLogin())
                .append(getPassword(), account.getPassword())
                .append(getUserRole(), account.getUserRole())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getFirstName())
                .append(getLastName())
                .append(getLogin())
                .append(getPassword())
                .append(getUserRole())
                .toHashCode();
    }


}
