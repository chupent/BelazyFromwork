package com.belazy.library.constant;

/**
 * @author tangcp
 */
public class RedisConstant {
    /**短信验证码存于Redis KEY前缀*/
    public static String LOGIN_SMS_CODE_KEY="redis:login:sms:code:key:";
    /**短信验证码存于Redis 失效时间(单位:秒)*/
    public static int LOGIN_SMS_CODE_EXPIRE=120;

}
