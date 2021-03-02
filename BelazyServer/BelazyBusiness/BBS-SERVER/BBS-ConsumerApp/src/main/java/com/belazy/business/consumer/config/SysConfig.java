package com.belazy.business.consumer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author tangcp
 */
@Configuration
public class SysConfig {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
