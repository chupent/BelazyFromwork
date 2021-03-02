package com.belazy.business.test.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tangcp
 */
@Slf4j
@RestController
public class TestController {
    @GetMapping(value = "/echo/{string}")
    public String echo(@PathVariable String string) {
        log.info ("============================");
        return "Hello Nacos Discovery " + string;
    }
}
