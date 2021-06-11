package com.belazy.business.common.entity;


import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;

/**
 * 用户角色关联表
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月11日 15:58:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@ApiModel(description="用户角色关联表")
@TableName("sys_user_role")
public class SysUserRoleEntity implements Serializable{
	private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "角色ID")
	private String roleId;
    @ApiModelProperty(value = "用户ID")
	private String userId;

}
