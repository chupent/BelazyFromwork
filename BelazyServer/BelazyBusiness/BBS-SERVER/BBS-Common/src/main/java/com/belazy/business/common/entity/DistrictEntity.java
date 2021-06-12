package com.belazy.business.common.entity;


import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;

/**
 * 系统-大区
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月12日 10:18:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@ApiModel(description="系统-大区")
@TableName("sys_district")
public class DistrictEntity implements Serializable{
	private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "编号")
	private String code;
    @ApiModelProperty(value = "主键ID")
	@TableId(value = "id", type = IdType.ASSIGN_UUID)
	private Long id;
    @ApiModelProperty(value = "大区名称")
	private String name;

}
