package com.belazy.business.consumer;

import com.belazy.library.core.log.ConsoleUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;

import java.net.UnknownHostException;

/**
 * @author tangcp
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ConsumerAppRunApplication {
    public static void main(String[] args)  {
        ApplicationContext applicationContext = SpringApplication.run (ConsumerAppRunApplication.class, args);
        ConsoleUtil.startBusinessServerLog (applicationContext);
    }
}
