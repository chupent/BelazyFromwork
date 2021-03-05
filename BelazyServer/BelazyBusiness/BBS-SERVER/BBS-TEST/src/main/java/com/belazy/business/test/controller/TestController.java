package com.belazy.business.test.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tangcp
 */
@Slf4j
@RestController
@Api(tags = "测试相关控制类")
public class TestController {
    @ApiOperation (value = "测试接口")
    @GetMapping(value = "/echo/{string}")
    public String echo(@PathVariable String string) {
        log.info ("============================");
        return "Hello Nacos Discovery " + string;
    }
}
