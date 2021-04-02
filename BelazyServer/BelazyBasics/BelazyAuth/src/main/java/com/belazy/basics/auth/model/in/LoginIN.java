package com.belazy.basics.auth.model.in;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 账号密码登录
 * @author tangcp
 */
@Data
public class LoginIN implements Serializable {
    @ApiModelProperty(value = "登录账号",required = true)
    private String username;
    @ApiModelProperty(value = "登录来源:App手机应用 Web应用",required = true)
    private String source;

    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("短信验证码")
    private String smsCode ;
    @ApiModelProperty("设备系统版本")
    private String sysVersion;
    @ApiModelProperty("设备ID")
    private String deviceId;
    @ApiModelProperty("设备版本")
    private String deviceVersion;
    @ApiModelProperty("App版本")
    private String appVersion;
}
