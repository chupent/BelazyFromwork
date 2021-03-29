package com.belazy.basics.auth;

import com.belazy.library.core.log.ConsoleUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author tangcp
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.belazy"})
@MapperScan("com.belazy.basics.auth.mapper")
public class AuthRunApplicion {
    public static void main(String[] args) {
        SpringApplication.run (AuthRunApplicion.class,args);
    }
}
