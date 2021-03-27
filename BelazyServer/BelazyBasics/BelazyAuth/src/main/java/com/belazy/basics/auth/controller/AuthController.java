package com.belazy.basics.auth.controller;

import com.belazy.library.core.basics.Result;
import com.belazy.library.core.constant.RedisConstant;
import com.belazy.library.redis.service.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author tangcp
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/auth")
@Slf4j
public class AuthController {
    final private RedisService redisService;
    @PostMapping("/sendSmsCode")
    public Result<Boolean> sendSmsCode(@RequestParam("moible") String moible) {
        Object obj = redisService.get (RedisConstant.LOGIN_SMS_CODE_KEY + moible);
        if (!StringUtils.isEmpty (obj)) {
            return Result.fail ("已发送短信验证码，请稍后再试!");
        }
        redisService.set (RedisConstant.LOGIN_SMS_CODE_KEY + moible, "123456", RedisConstant.LOGIN_SMS_CODE_EXPIRE);
        return Result.success (true);
    }
}
