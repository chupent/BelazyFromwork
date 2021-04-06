package com.belazy.basics.gateway.filter;

import com.belazy.basics.gateway.util.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import org.apache.commons.lang3.StringUtils;

/**
 * AUTO鉴权
 *
 * @author tangcp
 */
@Slf4j
@Component
public class AuthFiler implements GlobalFilter, Ordered {
    @Autowired
    private RedisTemplate<String, String> jsonRedisTemplate;
    
    //开发资源
    private static final String[] OPEN_RESOURCES = new String[]{"/v2/api-docs"};

    private boolean checkOpenApi(String openApi) {
        for (String openResources : OPEN_RESOURCES) {
            if (StringUtils.contains (openApi, openResources)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest ();
        ServerHttpResponse response = exchange.getResponse ();

        // 过滤公开资源
        String url = request.getPath ().toString ();
        String openApi = url.substring (url.indexOf ("/", 1));
        if (checkOpenApi (openApi)) {
            return HttpUtil.httpInfoLog (exchange, chain);
        }

        //Token校验
        HttpHeaders headers = request.getHeaders ();
        String token = headers.getFirst (HttpHeaders.AUTHORIZATION);
        if (StringUtils.isEmpty (token)) {
            return HttpUtil.buildUnauthorizedResponse (response);
        }

        String tokenValue = token.split (" ")[1];
        Object accessTokenExpire = jsonRedisTemplate.opsForValue ().get ("access:" + tokenValue);
        if (null == accessTokenExpire) {
            return HttpUtil.buildUnauthorizedResponse (response);
        }
        response.getHeaders ().add (HttpHeaders.AUTHORIZATION, token);
        return HttpUtil.httpInfoLog (exchange, chain);
    }
    @Override
    public int getOrder() {
        return -100;
    }
}
