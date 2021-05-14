package com.belazy.basics.gateway.config.ribbon;

import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;

/**
 *
 */
public class RuleConfigure {
    @Bean
    public IRule isolationRule() {
        return new CustomIsolationRule();
    }
}
