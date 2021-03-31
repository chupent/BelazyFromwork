package com.belazy.business.test.controller;

import com.belazy.business.test.model.UserEntity;
import com.belazy.library.web.util.TokenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author tangcp
 */
@Slf4j
@RestController
@Api(tags = "测试相关控制类")
public class TestController {
    @ApiOperation (value = "测试接口1")
    @PostMapping(value = "/echo")
    public String echo1(@RequestBody UserEntity user) {
        log.info ("============================");
        return "Hello Nacos Discovery username:" + user.getUsername ()+",age:"+user.getAge ();
    }
    @ApiOperation (value = "测试接口2")
    @GetMapping(value = "/echo/2")
    public String echo2(@RequestParam String user) {
        TokenUtils.getUserId ();
        return "Hello Nacos Discovery username:" + user;
    }
}
