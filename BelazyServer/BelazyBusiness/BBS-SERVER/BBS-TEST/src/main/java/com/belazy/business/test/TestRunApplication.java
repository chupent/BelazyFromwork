package com.belazy.business.test;

import com.belazy.library.util.log.ConsoleUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author tangcp
 */
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = {"com.belazy"})
@EnableFeignClients(basePackages = {"com.belazy.business.api.feign"})
public class TestRunApplication {
    public static void main(String[] args)  {
        ApplicationContext applicationContext = SpringApplication.run (TestRunApplication.class,args);
        ConsoleUtil.startBusinessServerLog (applicationContext);
    }
}
