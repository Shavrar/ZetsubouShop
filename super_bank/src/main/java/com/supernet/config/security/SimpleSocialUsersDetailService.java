package com.supernet.config.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;

/**
 * Simple social users detail service
 */
public class SimpleSocialUsersDetailService
        implements SocialUserDetailsService {

    private UserDetailsService userDetailsService;

    /**
     * Default constructor
     *
     * @param userDetailsService user detail service
     */
    public SimpleSocialUsersDetailService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public SocialUserDetails loadUserByUserId(String userId) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(userId);
        return new SocialUser(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
    }
}
