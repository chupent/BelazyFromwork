package com.belazy.library.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 用户信息
 * @author tangcp
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDTO implements Serializable {
    @ApiModelProperty(value = "用户ID",required = true)
    private String id;
    @ApiModelProperty(value = "用户账号",required = true)
    private String account;
    @ApiModelProperty(value = "用户姓名",required = true)
    private String mobile;
    @ApiModelProperty(value = "用户手机号",required = true)
    private String email;
    @ApiModelProperty(value = "用户角色",required = true)
    private List<String> roles;
}
