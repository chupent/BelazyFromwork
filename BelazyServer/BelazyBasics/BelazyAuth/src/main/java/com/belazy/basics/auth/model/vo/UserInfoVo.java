package com.belazy.basics.auth.model.vo;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

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
public class UserInfoVo implements Serializable {
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
    @ApiModelProperty(value = "用户角色",required = true)
    private List<String> roles;
}
