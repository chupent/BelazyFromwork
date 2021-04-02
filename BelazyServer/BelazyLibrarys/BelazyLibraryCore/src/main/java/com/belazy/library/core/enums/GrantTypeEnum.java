package com.belazy.library.core.enums;

/**
 * @author tangcp
 */
public enum GrantTypeEnum {
    SMS_CODE("sms_code"),
    PASSWORD("password");
    public String val;
    GrantTypeEnum(String val) {
        this.val = val;
    }
}
