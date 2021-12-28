package com.belazy.library.swagger.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.*;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author tangcp
 */
@Configuration
@EnableOpenApi
@EnableKnife4j
public class Swagger3Config {
    @Value ("${spring.application.name}")
    private String appName;
    // 定义分隔符,配置Swagger多包
    private static final String splitor = ";";
    @Bean
    public Docket createRestApi() {
        return new Docket (DocumentationType.OAS_30)
                .apiInfo (apiInfo ())
                .select ()
                .apis (RequestHandlerSelectors.withClassAnnotation (RestController.class))
                .apis (basePackage ("com.belazy.business;com.belazy.basics"))
                .paths (PathSelectors.any ())
                .build ()
                .globalRequestParameters (getGlobalRequestParameters ())
                .globalResponses (HttpMethod.GET, getGlobalResponseMessage ())
                .globalResponses (HttpMethod.POST, getGlobalResponseMessage ())
                .globalResponses (HttpMethod.DELETE, getGlobalResponseMessage ())
                .globalResponses (HttpMethod.PUT, getGlobalResponseMessage ());

    }


    public Predicate<RequestHandler> basePackage(String basePackage) {
        return input->  declaringClass (input).map (handlerPackage (basePackage)).orElse (true);
    }
    private Optional<Class<?>> declaringClass(RequestHandler input) {
        return Optional.ofNullable (input.declaringClass ());
    }
    private Function<Class<?>, Boolean> handlerPackage(final String basePackage) {
        return input -> {
            // 循环判断匹配
            for (String strPackage : basePackage.split(splitor)) {
                boolean isMatch = input.getPackage().getName().startsWith(strPackage);
                if (isMatch) {
                    return true;
                }
            }
            return false;
        };
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder ()
                .title (appName + " API接口文档")
                .contact (new Contact ("chupengt", "", "tangchupeng@163.com"))
                .version ("1.0")
                .description ("API文档")
                .build ();
    }

    /**
     * 生成全局通用参数
     *
     * @return
     */
    private List<RequestParameter> getGlobalRequestParameters() {
        List<RequestParameter> parameters = new ArrayList<RequestParameter> ();
        parameters.add (new RequestParameterBuilder ()
                .name ("Authorization")
                .description ("令牌")
                .required (false)
                .in (ParameterType.HEADER)
                .build ());
        return parameters;
    }

    /**
     * 生成通用响应信息
     *
     * @return
     */
    private List<Response> getGlobalResponseMessage() {
        List<Response> responseList = new ArrayList<Response> ();
        responseList.add (new ResponseBuilder ().code ("404").description ("找不到资源").build ());
        return responseList;
    }
}
