package com.belazy.basics.gateway.config.ribbon;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * 负载均衡隔离规则过滤器
 */
@Slf4j
@Component
public class LbIsolationFilter implements WebFilter,Ordered {

    @Value("${finance.ribbon.isolation.enabled:false}")
    private boolean enableIsolation;

    private static final String FINANCE_VERSION = "Belazy-Version";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
            if (enableIsolation) {
                String version = exchange.getRequest().getHeaders().getFirst(FINANCE_VERSION);

                if (StringUtils.isNotEmpty(version)) {
                    LbIsolationContextHolder.setVersion(version);
                }
            }

            return chain.filter(exchange).doFinally(signalType ->   LbIsolationContextHolder.clear());
    }


    @Override
    public int getOrder() {
        return -10000;
    }

}