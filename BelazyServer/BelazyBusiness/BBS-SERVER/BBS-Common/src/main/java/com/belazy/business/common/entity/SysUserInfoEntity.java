package com.belazy.business.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;

/**
 * 用户信息表
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月11日 15:34:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@ApiModel(description="用户信息表")
@TableName("sys_user_info")
public class SysUserInfoEntity implements Serializable{
	private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "年龄")
	private Integer age;
    @ApiModelProperty(value = "头像")
	private String avatar;
    @ApiModelProperty(value = "生日")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")	
	private Date birthday;
    @ApiModelProperty(value = "创建人")
	private Long createBy;
    @ApiModelProperty(value = "创建部门")
	private Long createDept;
    @ApiModelProperty(value = "")
	private String createName;
    @ApiModelProperty(value = "创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")	
	private Date createTime;
    @ApiModelProperty(value = "所属部门ID")
	private String deptId;
    @ApiModelProperty(value = "邮箱")
	private String email;
    @ApiModelProperty(value = "主键")
	@TableId(value = "id", type = IdType.ASSIGN_UUID)
	private String id;
    @ApiModelProperty(value = "真名")
	private String name;
    @ApiModelProperty(value = "昵称")
	private String nickname;
    @ApiModelProperty(value = "手机")
	private String phone;
    @ApiModelProperty(value = "性别 1男 2女")
	private Integer sex;
    @ApiModelProperty(value = "用户类型 0-PC用户 1-移动端用户")
	private Integer type;
    @ApiModelProperty(value = "修改人")
	private Long updateBy;
    @ApiModelProperty(value = "")
	private String updateName;
    @ApiModelProperty(value = "修改时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")	
	private Date updateTime;
    @ApiModelProperty(value = "登录账户ID")
	private String userId;

}
