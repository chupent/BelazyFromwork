package com.belazy.basics.gateway.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author tangcp
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "bgateway")
public class GatewayConfig {
    private String dataId;
    private String group;
    private List<String> openResources;
}
