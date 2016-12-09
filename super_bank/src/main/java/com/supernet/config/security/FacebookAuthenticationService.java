package com.supernet.config.security;

import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.security.provider.OAuth2AuthenticationService;

/**
 * Facebook auth service
 */
public class FacebookAuthenticationService
        extends OAuth2AuthenticationService<Facebook> {

    /**
     * Default constructor
     *
     * @param apiKey    facebook api key
     * @param appSecret facebook api secret
     */
    public FacebookAuthenticationService(String apiKey, String appSecret) {
        super(new FacebookConnectionFactory(apiKey, appSecret));
    }

}
