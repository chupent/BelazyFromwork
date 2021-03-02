package com.belazy.basics.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author tangcp
 */
@EnableDiscoveryClient
@SpringBootApplication
public class BelazyGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run (BelazyGatewayApplication.class, args);
    }
}
