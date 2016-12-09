package com.supernet.config.i18n;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Use it we may work with Spring Container
 */
@Component
public class ApplicationContextProvider
        implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    private static void setContext(ApplicationContext context) {
        applicationContext = context;
    }

    /**
     * Get bean by Name.class
     *
     * @param requiredType class
     * @param <T>          categories
     * @return <T>, bean from container
     */
    public static <T> T getBean(Class<T> requiredType) {
        return applicationContext.getBean(requiredType);
    }

    /**
     * Get bean by name
     *
     * @param name         bean name
     * @param requiredType class
     * @param <T>          categories
     * @return <T>, bean from container
     */
    public static <T> T getBean(String name, Class<T> requiredType) {
        return applicationContext.getBean(name, requiredType);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        ApplicationContextProvider.setContext(applicationContext);
    }
}