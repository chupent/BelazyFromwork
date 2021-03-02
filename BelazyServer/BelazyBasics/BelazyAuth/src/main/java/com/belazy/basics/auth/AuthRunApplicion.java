package com.belazy.basics.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
/**
 * @author tangcp
 */
@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
public class AuthRunApplicion {
    public static void main(String[] args) {
        SpringApplication.run (AuthRunApplicion.class,args);
    }
}
