package com.belazy.basics.gateway.route;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * Nacos实现动态路由配置监听器
 * @author tangcp
 */
@Slf4j
@Component
public class NacosDynamicRouteService implements ApplicationEventPublisherAware {
    private static final List<String> ROUTE_LIST = new ArrayList<String> ();//路由列表
    private ApplicationEventPublisher applicationEventPublisher;
    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private RouteDefinitionWriter routeDefinitionWriter;

    @Value("${spring.cloud.nacos.config.server-addr}")
    private String serverAddr;
    @Value("${bgateway.dataId}")
    private String dataId;//Nacos注册中心文件dataId
    @Value("${bgateway.group}")
    private String group;//Nacos注册中心文件分组

    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
        log.info ("dataId:"+dataId);
        log.info ("group:"+group);
        log.info ("serverAddr:"+serverAddr);
    }

    private void publish() {
        this.applicationEventPublisher.publishEvent(new RefreshRoutesEvent (this.routeDefinitionWriter));
    }
    private void clearRoute() { //清除路由
        for (String id : ROUTE_LIST) {
            this.routeDefinitionWriter.delete(Mono.just(id)).subscribe();
        }
        ROUTE_LIST.clear();
    }
    private void addRoute(RouteDefinition definition) {//新增路由
        try {
            routeDefinitionWriter.save(Mono.just(definition)).subscribe();
            ROUTE_LIST.add(definition.getId());
        } catch (Exception e) {
            log.error("addRoute error" + e);
        }
    }
    /**
     * 批量 添加及发布 路由
     * @param configInfo 配置文件字符串, 必须为json array格式
     */
    private void addAndPublishBatchRoute(String configInfo){
        log.info("Dynamic config gateway route start. {}", configInfo);
        clearRoute();
        if(StringUtils.isEmpty (configInfo)){
            log.error ("Dynamic config gateway route add fail.....");
            return;
        }
        try {
            List<RouteDefinition> gatewayRouteDefinitions =  mapper.readValue (configInfo, new TypeReference<List<RouteDefinition>> () {});
            for (RouteDefinition routeDefinition : gatewayRouteDefinitions) {
                addRoute(routeDefinition);
            }
            publish();
            log.info("Dynamic config gateway route finished. {}", gatewayRouteDefinitions);
        } catch (JsonProcessingException e) {
            log.error("json to list error" + e);
        }
    }

    /**
     *
     *
     * @PostConstruct被用来修饰一个非静态的void（）方法,@PostConstruct修饰的方法会在服务器加载Servlet的时候运行，
     * 并且只会被服务器执行一次。PostConstruct在构造函数之后执行，init（）方法之前执行。
     * 通常我们会是在Spring框架中使用到@PostConstruct注解 该注解的方法在整个Bean初始化中的执行顺序：
     * Constructor(构造方法) -> @Autowired(依赖注入) -> @PostConstruct(注释的方法)
     */
    @PostConstruct
    public void dynamicRouteByNacosListener(){
        try {
            ConfigService configService =  NacosFactory.createConfigService (serverAddr);
            String initConfigInfo = configService.getConfig (dataId, group, 5000);
            addAndPublishBatchRoute (initConfigInfo); //初始化路由

            configService.addListener (dataId, group, new Listener () {
                public Executor getExecutor() {
                    return null;
                }

                public void receiveConfigInfo(String configInfo) {
                    addAndPublishBatchRoute (configInfo);
                }
            });
        } catch (NacosException e) {
            log.error("dynamicRouteByNacosListener error" + e);
        }
    }
}
