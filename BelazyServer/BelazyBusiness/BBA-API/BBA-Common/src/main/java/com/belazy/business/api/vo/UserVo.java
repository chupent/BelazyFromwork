package com.belazy.business.api.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author tangcp
 */
@Data
@ToString
public class UserVo implements Serializable {
    @ApiModelProperty(value = "用户ID",required = true)
    private String id;
    @ApiModelProperty(value = "用户账号",required = true)
    private String account;
    @ApiModelProperty(value = "用户姓名",required = true)
    private String name;
    @ApiModelProperty(value = "用户手机号",required = true)
    private String phone;
    @ApiModelProperty(value = "用户昵称")
    private String nickname;
}
