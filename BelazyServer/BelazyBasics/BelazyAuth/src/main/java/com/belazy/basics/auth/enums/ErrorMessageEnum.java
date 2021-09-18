package com.belazy.basics.auth.enums;
/**
 * @version: 1.0
 * @author:TangChuPeng
 * @date:2021-9-18 12:52
 * @description: 错误信息枚举
 */
public enum ErrorMessageEnum {
    INVALID_GRANT(400,"授权失败，账号或密码错误！"),
    INTERNAL_SERVER_ERROR(500,"服务器异常"),
    ACCESS_DENIED(403,"拒绝访问"),
    AUTH_EXCEPTION(400,"授权异常"),
    HTTP_REQUEST_METHOD_NOT_SUPPORTED(400,"HTTP请求方法异常"),
    SMS_CODE_EXPIRE(400,"验证码失效，请重新发送！"),
    SMS_CODE_ERROR(400,"验证码不正确！"),
    PARAMS_ERROR(400,"请求参数错误！"),


    ;
    public final int CODE;
    public final String MESSAGE;

    private ErrorMessageEnum(int code, String message) {
        this.CODE = code;
        this.MESSAGE = message;
    }
}
