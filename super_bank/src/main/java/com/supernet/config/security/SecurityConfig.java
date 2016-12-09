package com.supernet.config.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.social.UserIdSource;
import org.springframework.social.security.AuthenticationNameUserIdSource;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * Security Configuration.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig
        extends WebSecurityConfigurerAdapter {

    private static Logger scLogger = LoggerFactory.getLogger(SecurityConfig.class);
    @Value("${web.security.rememberme}")
    private String rememberMeKey;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private HttpAuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private RestLogoutSuccessHandler logoutSuccessHandler;
    @Autowired
    private UserDetailsService userService;

    /**
     * Jdbc authentication
     *
     * @param auth authentication manager builder
     */
    //todo
    @Autowired
    public void registerAuthentication(AuthenticationManagerBuilder auth) {
        try {
            auth.jdbcAuthentication()
                    .dataSource(dataSource)
                    .usersByUsernameQuery("select login, password,'true' from accounts where login = ?")
                    .authoritiesByUsernameQuery("select login, user_role from accounts where login = ?")
                    .passwordEncoder(passwordEncoder());
        } catch (Exception e) {
            scLogger.error("bad jdbc auth", e);
        }
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .httpBasic()
                .authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .headers()
                .cacheControl().disable()
                .and()
                .exceptionHandling()
                .and()
                .authorizeRequests()
                .antMatchers("/api/**").permitAll()
                .and()
                .apply(new SpringSocialConfigurer())
                .and()
                .rememberMe()
                .rememberMeServices(rememberMeServices()).tokenValiditySeconds(1209600)
                .key(rememberMeKey)
                .and()
                .logout()
                .invalidateHttpSession(true)
                .logoutUrl("/api/accounts/logout")
                .logoutSuccessHandler(logoutSuccessHandler)
                .and();
    }

    /**
     * Remember me service
     *
     * @return TokenBasedRememberMeService
     */
    @Bean
    public TokenBasedRememberMeServices rememberMeServices() {
        TokenBasedRememberMeServices rememberMeServices = new TokenBasedRememberMeServices(rememberMeKey, userService);
        rememberMeServices.setCookieName("rememberMe-cookie");
        rememberMeServices.setParameter("rememberMe");
        return rememberMeServices;
    }

    /**
     * Get service for check if user exist
     *
     * @return SocialUserDetailsService
     */
    @Bean
    public SocialUserDetailsService socialUsersDetailService() {
        return new SimpleSocialUsersDetailService(userDetailsService());
    }

    /**
     * Get source for user id
     *
     * @return UserIdSource
     */
    @Bean
    public UserIdSource userIdSource() {
        return new AuthenticationNameUserIdSource();
    }

    /**
     * Get password encoder
     *
     * @return PasswordEncoder
     */
    //todo
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    /**
     * Get text encruptor
     *
     * @return TextEncryptor
     */
    @Bean
    public TextEncryptor textEncryptor() {
        return Encryptors.noOpText();
    }

}
