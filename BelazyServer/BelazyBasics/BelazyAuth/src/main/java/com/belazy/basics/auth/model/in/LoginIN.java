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
    @ApiModelProperty(value = "授权类型 ：短信登录grantType=sms_code 、 密码登录grantType=password")
    private String grantType;
    @ApiModelProperty("密码(GRANT_TYPE=password时必填)")
    private String password;
    @ApiModelProperty("短信验证码(GRANT_TYPE=sms_code时必填)")
    private String smsCode ;
    @ApiModelProperty("App访问设备版本类型，web为访问浏览器")
    private String browser;
    @ApiModelProperty("设备ID，web访问为电脑MAC地址")
    private String deviceId;
    @ApiModelProperty("访问端客户端版本")
    private String version;
    @ApiModelProperty("登录地")
    private String loginLocation;
}
