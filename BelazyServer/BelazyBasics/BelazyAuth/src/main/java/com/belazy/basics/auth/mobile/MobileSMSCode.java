package com.belazy.basics.auth.mobile;

import lombok.*;

import java.io.Serializable;

/**
 * 手机号+短信验证码入参数据模型
 * @author tangcp
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class MobileSMSCode implements Serializable {
    private String username;//用户账号
    private String smsCode ;//短信验证码
    private String sysVersion;//设备系统版本
    private String deviceId;//设备ID
    private String deviceVersion;//设备版本
    private String appVersion;//App本部
}
