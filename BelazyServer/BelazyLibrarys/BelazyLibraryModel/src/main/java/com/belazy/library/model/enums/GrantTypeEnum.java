package com.belazy.library.model.enums;

/**
 * 登录方式
 * @author tangcp
 */
public enum GrantTypeEnum {
    SMS_CODE("sms_code"),//短信验证码登录模式
    PASSWORD("password");//密码登录模式
    public String val;
    GrantTypeEnum(String val) {
        this.val = val;
    }
}
