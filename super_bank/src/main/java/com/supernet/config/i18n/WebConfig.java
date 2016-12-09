package com.supernet.config.i18n;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

/**
 * Config for i18n
 */
@Configuration
public class WebConfig
        extends WebMvcConfigurerAdapter {

    /**
     * Configure all properties
     *
     * @return ReloadableResourceBundleMessageSource
     */
    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:i18n/exceptions", "classpath:i18n/titles");
        LocaleContextHolder.setLocale(Locale.ENGLISH);
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    /**
     * Set locale in cookie
     *
     * @return CookieLocaleResolver
     */
    @Bean
    public CookieLocaleResolver localeResolver() {
        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        localeResolver.setCookieName("my-locale-cookie");
        localeResolver.setCookieMaxAge(3600);
        return localeResolver;
    }

    /**
     * Change locale
     *
     * @return LocaleChangeInterceptor
     */
    @Bean
    public LocaleChangeInterceptor localeInterceptor() {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");
        return interceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeInterceptor());
    }
}
