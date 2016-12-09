package com.supernet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Entry point
 */
@SpringBootApplication
@EnableAsync
public class SpringBootRestApplication
        extends SpringBootServletInitializer {
    private static Logger springLogger = LoggerFactory.getLogger(SpringBootRestApplication.class);

    /**
     * Main method
     *
     * @param args argument
     */
    public static void main(String[] args) {
        springLogger.info("Booting with " + SpringBootRestApplication.class.getSimpleName());
        SpringApplication application = new SpringApplication(SpringBootRestApplication.class);
        application.run(args);
        springLogger.info("Successfully started application: " + SpringBootRestApplication.class.getSimpleName());
    }
}