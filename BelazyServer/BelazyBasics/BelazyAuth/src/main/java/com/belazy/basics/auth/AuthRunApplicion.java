package com.belazy.basics.auth;

import com.belazy.library.core.log.ConsoleUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;

/**
 * @author tangcp
 */
@SpringBootApplication
@EnableDiscoveryClient
public class AuthRunApplicion {
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run (AuthRunApplicion.class,args);
        ConsoleUtil.startBasicServerLog (applicationContext);
    }
}
