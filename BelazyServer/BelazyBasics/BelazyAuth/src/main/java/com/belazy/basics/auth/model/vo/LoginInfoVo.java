package com.belazy.basics.auth.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

/**
 * Token凭证信息
 * @author tangcp
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginInfoVo implements Serializable {
    private String accessToken;
    private String tokenType;
    private String refreshToken;
    private String expiresIn;
    private String scope;
    private UserInfoVo userInfo;
}
