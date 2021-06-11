package com.belazy.business.common.entity;


import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;

/**
 * 用户与岗位关联表
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月11日 15:58:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@ApiModel(description="用户与岗位关联表")
@TableName("sys_user_post")
public class SysUserPostEntity implements Serializable{
	private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "岗位ID")
	private String postId;
    @ApiModelProperty(value = "用户ID")
	private String userId;

}
