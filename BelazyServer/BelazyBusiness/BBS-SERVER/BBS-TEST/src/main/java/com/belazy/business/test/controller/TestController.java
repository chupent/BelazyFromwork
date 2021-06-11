package com.belazy.business.test.controller;
import com.belazy.business.test.model.UserEntity;
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
}
