package com.belazy.basics.auth.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author tangcp
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "oauth")
public class AuthConfig {
    private String authUri;
    private String clientId;
    private String clientSecret;
}
