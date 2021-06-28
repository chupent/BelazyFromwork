package com.belazy.basics.gateway.config.ribbon;



import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Configuration;

/**
 * 负责均衡配置
 */
@Configuration
@ConditionalOnProperty(value = "belazy.ribbon.isolation.enabled", havingValue = "true")
@RibbonClients(defaultConfiguration = {RuleConfigure.class})
public class LbIsolationConfig {

}
