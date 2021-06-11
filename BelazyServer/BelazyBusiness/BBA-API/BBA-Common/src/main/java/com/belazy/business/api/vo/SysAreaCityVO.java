package com.belazy.business.api.vo;


import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * 系统-省份城市区域表
 *
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月11日 15:27:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@ApiModel(description = "系统-省份城市区域表")
public class SysAreaCityVO implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "代码")
    private String code;
    @ApiModelProperty(value = "主键ID")
    private Long id;
    @ApiModelProperty(value = "层级，1=省，2=市，3=区")
    private Integer lvl;
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "上级代码")
    private String parentCode;
}
