package com.supernet.config.mapping;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Mapping config
 */
@Configuration
public class MappingConfig {

    /**
     * Create Model Mapper
     *
     * @return Model Mapper
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
