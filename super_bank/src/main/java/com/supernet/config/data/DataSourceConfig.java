package com.supernet.config.data;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Config DataSource
 */
@Configuration
public class DataSourceConfig {

    /**
     * Create rest data source
     *
     * @return DataSource
     */
    @Bean(name = "datasource")
    @ConfigurationProperties(prefix = "spring.datasource")
    //http://commons.apache.org/proper/commons-dbcp/configuration.html
    public DataSource restDataSource() {
        return new BasicDataSource();
    }
}
