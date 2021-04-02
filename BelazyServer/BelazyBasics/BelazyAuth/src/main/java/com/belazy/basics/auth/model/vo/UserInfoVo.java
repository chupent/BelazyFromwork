package com.belazy.basics.auth.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

/**
 * 登录响应信息
 * @author tangcp
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInfoVo implements Serializable {
    String accessToken;
    String tokenType;
    String refreshToken;
    String expiresIn;
    String scope;
}
