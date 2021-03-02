package com.belazy.business.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author tangcp
 */
@Slf4j
@EnableDiscoveryClient
@SpringBootApplication
public class TestRunApplication {
    public static void main(String[] args) throws UnknownHostException {
        ApplicationContext applicationContext = SpringApplication.run (TestRunApplication.class,args);
        Environment env = applicationContext.getEnvironment ();
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        String port = env.getProperty("server.port");//端口号
        String appName = env.getProperty("spring.application.name");//服务名称
        String ipAddress = InetAddress.getLocalHost().getHostAddress();//IP地址
        log.info("\n----------------------------------------------------------\n\t" +
                        "Application '{}' is running! Access URLs:\n\t" +
                        "External: \thttp://{}:{}\n\t" +
                        "SwaggerUI: \thttp://localhost:{}/swagger-ui.html\n" +
                        "----------------------------------------------------------",
                appName, ipAddress, port, port);
    }
}
