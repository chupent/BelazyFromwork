package com.belazy.business.common.entity;


import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;

/**
 * 系统-省份城市区域表
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月12日 10:18:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@ApiModel(description="系统-省份城市区域表")
@TableName("sys_area_city")
public class AreaCityEntity implements Serializable{
	private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "代码")
	private String code;
    @ApiModelProperty(value = "主键ID")
	@TableId(value = "id", type = IdType.ASSIGN_UUID)
	private Long id;
    @ApiModelProperty(value = "层级，1=省，2=市，3=区")
	private Integer lvl;
    @ApiModelProperty(value = "名称")
	private String name;
    @ApiModelProperty(value = "上级代码")
	private String parentCode;

}
