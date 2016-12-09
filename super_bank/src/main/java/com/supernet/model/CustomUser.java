package com.supernet.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * Created by User on 05.12.2016.
 */
public class CustomUser extends User
{

    private final int userID;

    public int getUserID() {
        return userID;
    }

    public CustomUser(String username, String password, boolean enabled, boolean accountNonExpired,
                      boolean credentialsNonExpired,
                      boolean accountNonLocked,
                      Collection<? extends GrantedAuthority> authorities, int userID)
    {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.userID = userID;
    }
}
