package com.belazy.business.test;

import com.belazy.library.core.log.ConsoleUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;

/**
 * @author tangcp
 */
@EnableDiscoveryClient
@SpringBootApplication
public class TestRunApplication {
    public static void main(String[] args)  {
        ApplicationContext applicationContext = SpringApplication.run (TestRunApplication.class,args);
        ConsoleUtil.startBusinessServerLog (applicationContext);
    }
}
