package com.belazy.business.api.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * 用户登录账号
 *
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月11日 13:04:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@ApiModel(description = "用户登录账号")
public class SysUserVO implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "登录账号")
    private String account;
    @ApiModelProperty(value = "创建人")
    private Long createBy;
    @ApiModelProperty(value = "")
    private String createName;
    @ApiModelProperty(value = "创建时间/注册时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @ApiModelProperty(value = "登录邮箱")
    private String email;
    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "指示用户是启用还是禁用,0启用 1禁用")
    private Integer isAccountEnabled;
    @ApiModelProperty(value = "指示用户的帐户是否已过期：0未过期 1已过期")
    private Integer isAccountExpired;
    @ApiModelProperty(value = "指示用户是锁定还是解锁：0解锁 1锁定")
    private Integer isAccountLocked;
    @ApiModelProperty(value = "指示用户的凭据（密码）是否已过期：0未过期 1过期")
    private Integer isCredentialsExpired;
    @ApiModelProperty(value = "是否已删除 1删除 0未删除")
    private Integer isDelete;
    @ApiModelProperty(value = "登录密码")
    private String password;
    @ApiModelProperty(value = "登录手机号")
    private String phoneNumber;
    @ApiModelProperty(value = "注册设备(mac地址、手机设备号)")
    private String registerDevice;
    @ApiModelProperty(value = "注册类型 1——PC注册 、2——移动端注册、0——管理员预设(默认)")
    private Integer registerType;
    @ApiModelProperty(value = "更新人ID")
    private Long updateBy;
    @ApiModelProperty(value = "更新人姓名")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateName;
    @ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}
