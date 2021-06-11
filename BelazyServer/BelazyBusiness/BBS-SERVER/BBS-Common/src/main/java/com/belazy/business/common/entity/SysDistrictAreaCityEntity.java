package com.belazy.business.common.entity;


import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;

/**
 * 系统-大区-省份关联表
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月11日 15:58:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@ApiModel(description="系统-大区-省份关联表")
@TableName("sys_district_area_city")
public class SysDistrictAreaCityEntity implements Serializable{
	private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "主键ID")
	@TableId(value = "id", type = IdType.ASSIGN_UUID)
	private Long id;
    @ApiModelProperty(value = "省份城市区域表id")
	private Long sysAreaCityId;
    @ApiModelProperty(value = "大区表ID")
	private Long sysDistrictId;

}
