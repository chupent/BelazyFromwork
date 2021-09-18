package com.belazy.basics.gateway.route;

import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.belazy.basics.gateway.constant.NacosConstant;
import com.belazy.basics.gateway.properties.EnvironmentProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * Nacos实现动态路由配置监听器
 *
 * @author tangcp
 */
@Slf4j
@Component
public class DynamicRouteListener {
    private final ObjectMapper mapper;
    private final NacosDiscoveryProperties nacosDiscoveryProperties;
    private final NacosConfigProperties nacosConfigProperties;
    private final EnvironmentProperties environmentProperties;
    private final DynamicRouteService dynamicRouteService;

    public DynamicRouteListener(ObjectMapper mapper, NacosDiscoveryProperties nacosDiscoveryProperties, NacosConfigProperties nacosConfigProperties, EnvironmentProperties environmentProperties, DynamicRouteService dynamicRouteService) {
        this.mapper = mapper;
        this.nacosDiscoveryProperties = nacosDiscoveryProperties;
        this.nacosConfigProperties = nacosConfigProperties;
        this.environmentProperties = environmentProperties;
        this.dynamicRouteService = dynamicRouteService;
    }

    private void updateRoute(String configInfo) {
        log.info ("Dynamic config gateway route start. {}", configInfo);
        if (StringUtils.isEmpty (configInfo)) {
            log.error ("Dynamic config gateway route add fail.....");
            return;
        }
        try {
            List<RouteDefinition> gatewayRouteDefinitions = mapper.readValue (configInfo, new TypeReference<List<RouteDefinition>> () {
            });
            dynamicRouteService.updateList (gatewayRouteDefinitions);
            log.info ("Dynamic config gateway route finished. {}", gatewayRouteDefinitions);
        } catch (JsonProcessingException e) {
            log.error ("json to list error" + e);
        }
    }

    /**
     * @PostConstruct被用来修饰一个非静态的void（）方法,@PostConstruct修饰的方法会在服务器加载Servlet的时候运行， 并且只会被服务器执行一次。PostConstruct在构造函数之后执行，init（）方法之前执行。
     * 通常我们会是在Spring框架中使用到@PostConstruct注解 该注解的方法在整个Bean初始化中的执行顺序：
     * Constructor(构造方法) -> @Autowired(依赖注入) -> @PostConstruct(注释的方法)
     */
    @PostConstruct
    public void dynamicRouteByNacosListener() {
        try {
            String dataId = NacosConstant.dataId (environmentProperties.getName (), environmentProperties.getEnv (), NacosConstant.NACOS_CONFIG_JSON_FORMAT);
            String group = nacosConfigProperties.getGroup ();
            String serverAddr = nacosDiscoveryProperties.getServerAddr ();
            log.info ("从NACOS加载动态路由,加载文件{}-{}.{},组为{},服务地址{}", environmentProperties.getName (), environmentProperties.getEnv (), NacosConstant.NACOS_CONFIG_JSON_FORMAT, group, serverAddr);

            ConfigService configService = NacosFactory.createConfigService (serverAddr);
            String initConfigInfo = configService.getConfig (dataId, group, 5000);
            updateRoute (initConfigInfo); //初始化路由

            configService.addListener (dataId, group, new Listener () {
                public Executor getExecutor() {
                    return null;
                }

                public void receiveConfigInfo(String configInfo) {
                    updateRoute (configInfo);
                }
            });
        } catch (NacosException e) {
            log.error ("dynamicRouteByNacosListener error" + e);
        }
    }
}
