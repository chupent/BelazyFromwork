package com.belazy.library.util.log;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 控制台日志打印
 * @author tangcp
 */
@Slf4j
public class ConsoleUtil {
    public static void startBasicServerLog(ApplicationContext applicationContext)  {
        Environment env = applicationContext.getEnvironment ();
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        String port = env.getProperty("server.port");//端口号
        String appName = env.getProperty("spring.application.name");//服务名称
        String ipAddress = null;//IP地址
        try {
            ipAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace ();
            log.info ("ipAddress:{}","地址获取不到");
        }
        log.info("\n----------------------------------------------------------\n\t" +
                        "Application '{}' is running! Access URLs:\n\t" +
                        "External: \thttp://{}:{}/doc.html\n\t" +
                        "----------------------------------------------------------",
                appName, ipAddress, port);
    }

    public static void startBusinessServerLog(ApplicationContext applicationContext)  {
        Environment env = applicationContext.getEnvironment ();
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        String port = env.getProperty("server.port");//端口号
        String appName = env.getProperty("spring.application.name");//服务名称
        String ipAddress = null;//IP地址
        try {
            ipAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace ();
            log.info ("ipAddress:{}","地址获取不到");
        }
        log.info("\n----------------------------------------------------------\n\t" +
                        "Application '{}' is running! Access URLs:\n\t" +
                        "External: \thttp://{}:{}\n\t" +
                        "SwaggerUI: \thttp://localhost:{}/swagger-ui/index.html \n"+
                        "----------------------------------------------------------",
                appName, ipAddress, port, port,port);
    }
}
