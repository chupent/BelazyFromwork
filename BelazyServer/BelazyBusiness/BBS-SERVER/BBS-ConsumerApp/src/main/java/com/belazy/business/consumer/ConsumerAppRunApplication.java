package com.belazy.business.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author tangcp
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ConsumerAppRunApplication {
    public static void main(String[] args) {
        SpringApplication.run (ConsumerAppRunApplication.class, args);
    }
}
