package com.belazy.library.core.basics;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 全局响应数据模型
 *
 * @author tangcp
 */
@ApiModel(value = "系统全局数据响应模型")
@Data
public class Result<T> {
    public static final String SUCCESS_CODE = "200";
    public static final String SUCCESS_MESSAGE = "request success!";
    public static final String FAIL_CODE = "400";
    public static final String FAIL_MESSAGE = "request fail!";

    @ApiModelProperty(value = "响应码:200请求正常，非200请求异常")
    private String code;
    @ApiModelProperty(value = "响应消息")
    private String message;
    @ApiModelProperty(value = "响应内容")
    private T body;

    public static <T> Result<T> success(T body) {
        return success (body, null);
    }

    public static <T> Result<T> success(T body, String message) {
        String mMessage = null == message || message.length () <= 0 ? SUCCESS_MESSAGE : message;
        return new Result<T> (SUCCESS_CODE, mMessage, body);
    }

    public static Result fail() {
        return fail (FAIL_MESSAGE);
    }

    public static Result fail(String message) {
        return fail (FAIL_CODE, message);
    }

    public static Result fail(String code, String message) {
        String mMessage = null == message || message.length () <= 0 ? FAIL_MESSAGE : message;
        String mCode = null == code || code.length () <= 0 ? FAIL_CODE : code;
        return new Result (mCode, mMessage, null);
    }


    public Result() {
    }

    public Result(String code, String message, T body) {
        this.code = code;
        this.message = message;
        this.body = body;
    }
}
