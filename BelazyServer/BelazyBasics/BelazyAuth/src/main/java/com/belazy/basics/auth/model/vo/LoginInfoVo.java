package com.belazy.basics.auth.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Token凭证信息
 * @author tangcp
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel("登录响应数据模型")
public class LoginInfoVo implements Serializable {
    @ApiModelProperty(value = "Token凭证",required = true)
    private String accessToken;
    @ApiModelProperty(value = "Token凭证类型",required = true)
    private String tokenType;
    @ApiModelProperty(value = "刷新Token凭证",required = true)
    private String refreshToken;
    @ApiModelProperty(value = "Token凭证过期时间",required = true)
    private String expiresIn;
    @ApiModelProperty(value = "scope",required = true)
    private String scope;
    @ApiModelProperty(value = "用户信息",required = true)
    private UserInfoVo userInfo;
}
