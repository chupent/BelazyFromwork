package com.belazy.business.common;

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
@EnableFeignClients(basePackages = {"com.belazy.business.api.feign"})
@ComponentScan(basePackages = {"com.belazy"})
public class CommonRunApplication {
    public static void main(String[] args)  {
        ApplicationContext applicationContext = SpringApplication.run (CommonRunApplication.class, args);
        ConsoleUtil.startBusinessServerLog (applicationContext);
    }
}
