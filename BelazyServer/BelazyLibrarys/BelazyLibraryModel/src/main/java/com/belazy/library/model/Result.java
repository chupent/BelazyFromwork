package com.belazy.library.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 全局响应数据模型
 *
 * @author tangcp
 */
@ApiModel(value = "系统全局数据响应模型")
@Data
public class Result<T> implements Serializable {
    public static final String SUCCESS_CODE = "200";
    public static final String SUCCESS_MESSAGE = "request success!";
    public static final String FAIL_CODE = "400";
    public static final String FAIL_MESSAGE = "request fail!";

    @ApiModelProperty(value = "响应码:200请求正常，非200请求异常")
    private String code;
    @ApiModelProperty(value = "标记:true请求成功，false请求失败")
    private Boolean isSuccess;
    @ApiModelProperty(value = "响应消息")
    private String message;
    @ApiModelProperty(value = "响应内容")
    private T body;

    /**
     * 默认成功
     *
     * @param <T>
     * @return
     */
    public static <T> Result<T> success() {
        return success (null, SUCCESS_MESSAGE);
    }

    /**
     * 带数据模型成功
     *
     * @param body
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T body) {
        return success (body, SUCCESS_MESSAGE);
    }

    /**
     * 带数据模型、提示消息成功
     *
     * @param body
     * @param message
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T body, String message) {
        String mMessage = null == message || message.length () <= 0 ? SUCCESS_MESSAGE : message;
        return new Result<T> (SUCCESS_CODE, mMessage, body);
    }

    /**
     * 默认失败
     *
     * @return
     */
    public static Result fail() {
        return fail (FAIL_MESSAGE);
    }

    /**
     * 带提示失败
     *
     * @param message
     * @return
     */
    public static Result fail(String message) {
        return fail (FAIL_CODE, message);
    }

    /**
     * 带状态码、提示消息失败
     *
     * @param code
     * @param message
     * @return
     */
    public static Result fail(String code, String message) {
        String mMessage = null == message || message.length () <= 0 ? FAIL_MESSAGE : message;
        String mCode = null == code || code.length () <= 0 ? FAIL_CODE : code;
        return new Result (mCode, mMessage, null);
    }

    /**
     * 认证失败
     *
     * @return
     */
    public static Result unauthorized() {
        return Result.unauthorized (null);
    }

    /**
     * 认证失败
     *
     * @param message
     * @return
     */
    public static Result unauthorized(String message) {
        String mMessage = null == message || message.length () <= 0 ? "认证失败" : message;
        return Result.fail ("401", mMessage);
    }

    public Result() {
    }

    public Result(String code, String message, T body) {
        this.code = code;
        this.message = message;
        this.body = body;
        this.isSuccess = SUCCESS_CODE.equals (code) ? true : false;
    }
}
