package com.belazy.basics.auth.controller;

import com.belazy.library.core.basics.Result;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 错误全局处理
 * @author tangcp
 */
@RestController
public class SysExceptionController implements ErrorController {
    public String getErrorPath() {
        return "/error";
    }
    @RequestMapping("/error")
    public Result<Boolean> handleError(HttpServletRequest request){
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        HttpStatus httpStatus = HttpStatus.resolve (statusCode);
        return Result.fail (httpStatus.value ()+"",httpStatus.name ());
    }
}
