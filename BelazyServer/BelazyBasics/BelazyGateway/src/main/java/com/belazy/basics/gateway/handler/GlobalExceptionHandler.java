package com.belazy.basics.gateway.handler;

import com.belazy.library.model.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

/**
 * 全局异常处理
 * @author tangcp
 */
@Slf4j
@Order(-1)
@Component
@RequiredArgsConstructor
public class GlobalExceptionHandler implements ErrorWebExceptionHandler {
    private final ObjectMapper objectMapper;

    @Override
    public Mono<Void> handle(ServerWebExchange serverWebExchange, Throwable throwable) {
        ServerHttpResponse response = serverWebExchange.getResponse ();
        ServerHttpRequest  request = serverWebExchange.getRequest ();
        if (response.isCommitted ()) {
            return Mono.error (throwable);
        }
        // header set
        response.getHeaders ().setContentType (MediaType.APPLICATION_JSON);
        if (throwable instanceof ResponseStatusException) {
            response.setStatusCode (((ResponseStatusException) throwable).getStatus ());
        }
        log.error ("RequestURL:{}",request.getURI ());
        log.error ("ExceptionMessage:{}",throwable.getMessage ());
        return response.writeWith (Mono.fromSupplier (() -> {
            DataBufferFactory bufferFactory = response.bufferFactory ();
            try {
                return bufferFactory.wrap (objectMapper.writeValueAsBytes (Result.fail (throwable.getMessage ())));
            } catch (JsonProcessingException e) {
                log.error ("Error writing response", throwable);
                return bufferFactory.wrap (new byte[0]);
            }
        }));
    }
}
