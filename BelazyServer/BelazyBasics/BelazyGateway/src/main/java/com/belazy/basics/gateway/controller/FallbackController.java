package com.belazy.basics.gateway.controller;

import com.belazy.library.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 降级处理
 *
 * @author tangcp
 */
@Slf4j
@RestController
public class FallbackController {
    @RequestMapping("/defaultFallback")
    public Result<String> defaultFallback() {
        log.error ("服务触发熔断机制");
        return Result.fail ();
    }
}
