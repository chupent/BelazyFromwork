package com.belazy.basics.gateway;

import com.belazy.library.util.log.ConsoleUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author tangcp
 */
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = {"com.belazy"})
public class BelazyGatewayApplication {
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run (BelazyGatewayApplication.class, args);
        ConsoleUtil.startBasicServerLog (applicationContext);
    }
}
