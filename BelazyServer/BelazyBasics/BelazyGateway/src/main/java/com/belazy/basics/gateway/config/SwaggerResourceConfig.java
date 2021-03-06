package com.belazy.basics.gateway.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 聚合Swagger3文档
 * @author tangcp
 */
@Configuration
@Primary
@EnableOpenApi
@EnableKnife4j
public class SwaggerResourceConfig implements SwaggerResourcesProvider {
    public static final String API_URI = "/v2/api-docs";
    private final RouteLocator routeLocator;
    @Value("${spring.application.name}")
    private String applicationName;

    public SwaggerResourceConfig(RouteLocator routeLocator) {
        this.routeLocator = routeLocator;
    }
    @Override
    public List<SwaggerResource> get() {
        //Swagger3 API资源列表
        List<SwaggerResource> resources = new ArrayList<> ();
        //服务名称列表
        List<String> routeHosts = new ArrayList<>();

        // 获取所有可用的应用名称
        routeLocator.getRoutes()
                .filter(route -> route.getUri().getHost() != null) //过滤地址为null的
                .filter(route -> !applicationName.equals(route.getUri().getHost())) //过滤网关服务
                .subscribe(route -> routeHosts.add(route.getUri().getHost()));

        // 去重，多负载服务只添加一次
        Set<String> existsServer = new HashSet<> ();
        routeHosts.forEach(host -> {
            // 拼接url
            String url = "/" + host + API_URI;
            //不存在则添加
            if (!existsServer.contains(url)) {
                existsServer.add(url);
                resources.add(swaggerResource(host,url));
            }
        });
        return resources;
    }
    //构建Swagger资源
    private SwaggerResource swaggerResource(String name, String location) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion("2.0");
        return swaggerResource;
    }
}
