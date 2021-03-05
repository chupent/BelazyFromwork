package com.belazy.library.swagger.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import springfox.documentation.builders.*;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tangcp
 */
@Configuration
@EnableOpenApi
@EnableKnife4j
public class Swagger3Config {
    @Value ("${spring.application.name}")
    private String appName;
    @Bean
    public Docket createRestApi() {
        return new Docket (DocumentationType.OAS_30)
                .apiInfo (apiInfo ())
                .select ()
                .apis (RequestHandlerSelectors.basePackage ("com.belazy.business"))
                .paths (PathSelectors.any ())
                .build ()
                .globalRequestParameters (getGlobalRequestParameters ())
                .globalResponses (HttpMethod.GET, getGlobalResponseMessage ())
                .globalResponses (HttpMethod.POST, getGlobalResponseMessage ())
                .globalResponses (HttpMethod.DELETE, getGlobalResponseMessage ())
                .globalResponses (HttpMethod.PUT, getGlobalResponseMessage ());

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
                .name ("x-access-token")
                .description ("令牌")
                .required (false)
                .in (ParameterType.HEADER)
                .build ());

        parameters.add (new RequestParameterBuilder ()
                .name ("Equipment-Type")
                .description ("产品类型")
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
