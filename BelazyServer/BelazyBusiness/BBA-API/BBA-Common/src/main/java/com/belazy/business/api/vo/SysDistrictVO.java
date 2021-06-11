package com.belazy.business.api.vo;;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
/**
 * 系统-大区
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月11日 15:34:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@ApiModel(description="系统-大区")
public class SysDistrictVO implements Serializable{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号")
    private String code;
    @ApiModelProperty(value = "主键ID")
    private Long id;
    @ApiModelProperty(value = "大区名称")
    private String name;

}
