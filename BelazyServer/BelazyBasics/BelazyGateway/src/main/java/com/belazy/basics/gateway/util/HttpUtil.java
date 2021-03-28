package com.belazy.basics.gateway.util;

import com.belazy.library.core.basics.Result;
import com.belazy.library.core.constant.CommonConstant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.zip.GZIPInputStream;

/**
 * HTTP请求信息打印
 * @author tangcp
 */
@Slf4j
public class HttpUtil{

    public static Mono<Void> httpInfoLog(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest ();
        String method = request.getMethodValue ();
        log.info ("REQUEST URL:{}", request.getURI ());
        log.info ("REQUEST METHOD:{}", method);
        log.info ("REQUEST HEADER:{}", request.getHeaders ());

        if (HttpMethod.POST.matches (method)) {
            return DataBufferUtils.join (exchange.getRequest ().getBody ())
                    .flatMap (dataBuffer -> {
                        byte[] bytes = new byte[dataBuffer.readableByteCount ()];
                        dataBuffer.read (bytes);

                        String bodyString = new String (bytes, StandardCharsets.UTF_8);
                        log.info ("REQUEST BODY:{}", bodyString.trim ());//打印请求参数
                        exchange.getAttributes ().put ("POST_BODY", bodyString);

                        DataBufferUtils.release (dataBuffer);
                        Flux<DataBuffer> cachedFlux = Flux.defer (() -> {
                            DataBuffer buffer = exchange.getResponse ().bufferFactory ().wrap (bytes);
                            return Mono.just (buffer);
                        });

                        ServerHttpRequest mutatedRequest = new ServerHttpRequestDecorator (exchange.getRequest ()) {
                            @Override
                            public Flux<DataBuffer> getBody() {
                                return cachedFlux;
                            }
                        };
                        ServerHttpResponse response = getServerHttpResponseDecorator (exchange);
                        return chain.filter (exchange.mutate ().request (mutatedRequest).response (response).build ());
                    });
        }

        return chain.filter (exchange.mutate ().response (getServerHttpResponseDecorator (exchange)).build ());
    }

    public static ServerHttpResponseDecorator getServerHttpResponseDecorator(ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse ();
        DataBufferFactory bufferFactory = response.bufferFactory ();
        return new ServerHttpResponseDecorator (response) {
            @Override
            public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                if (body instanceof Flux) {
                    Flux<? extends DataBuffer> fluxBody = (Flux<? extends DataBuffer>) body;
                    return super.writeWith (fluxBody.buffer ().map (dataBuffer -> {
                        DataBufferFactory dataBufferFactory = new DefaultDataBufferFactory ();
                        DataBuffer join = dataBufferFactory.join (dataBuffer);
                        byte[] content = new byte[join.readableByteCount ()];
                        join.read (content);
                        DataBufferUtils.release (join); //释放掉内存
                        String s = new String (content, StandardCharsets.UTF_8);

                        List<String> strings = exchange.getResponse ().getHeaders ().get (HttpHeaders.CONTENT_ENCODING);
                        if (!CollectionUtils.isEmpty (strings) && strings.contains ("gzip")) {
                            GZIPInputStream gzipInputStream = null;
                            try {
                                gzipInputStream = new GZIPInputStream (new ByteArrayInputStream (content), content.length);
                                StringWriter writer = new StringWriter ();
                                IOUtils.copy (gzipInputStream, writer, StandardCharsets.UTF_8);
                                s = writer.toString ();
                            } catch (IOException e) {
                                log.error ("====Gzip IO error", e);
                            } finally {
                                if (gzipInputStream != null) {
                                    try {
                                        gzipInputStream.close ();
                                    } catch (IOException e) {
                                        log.error ("===Gzip IO close error", e);
                                    }
                                }
                            }
                        } else {
                            s = new String (content, StandardCharsets.UTF_8);
                        }
                        log.info ("RESPONSE BODY: {}", s);//打印请求响应值
                        return bufferFactory.wrap (content);
                    }));
                }
                return super.writeWith (body);
            }
        };
    }

    public static Mono<Void> buildUnauthorizedResponse(ServerHttpResponse response) {
        try {
            ObjectMapper objectMapper = new ObjectMapper ();
            byte[] bytes = objectMapper.writeValueAsBytes (Result.fail (HttpStatus.UNAUTHORIZED.value () + "", HttpStatus.UNAUTHORIZED.name ()));
            DataBuffer bodyDataBuffer = response.bufferFactory ().wrap (bytes);
            response.getHeaders ().add (HttpHeaders.CONTENT_TYPE, CommonConstant.HEADER_CONTENT_TYPE_VAL);

            return response.writeWith (Mono.just (bodyDataBuffer));
        } catch (JsonProcessingException e) {
            e.printStackTrace ();
        }
        return null;
    }
}
