package com.belazy.basics.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author tangcp
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "bgateway")
public class GatewayConfig {
    private String dataId;
    private String group;
}
