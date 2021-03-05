package com.belazy.business.consumer.controller;

import com.belazy.business.consumer.model.UserEntity;
import com.belazy.library.core.basics.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author tangcp
 */
@RestController
public class NacosController {
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private RestTemplate restTemplate;
    @Value("${spring.application.name}")
    private String appName;

    @GetMapping("/echo/app-name")
    public Result<UserEntity> echoAppName(){
        ServiceInstance serviceInstance = loadBalancerClient.choose("BBS-TEST");
        String path = String.format("http://%s:%s/echo/%s",serviceInstance.getHost(),serviceInstance.getPort(),appName);
        System.out.println("request path:" +path);
        return Result.success (new UserEntity (restTemplate.getForObject(path,String.class),"19"));
    }
}
