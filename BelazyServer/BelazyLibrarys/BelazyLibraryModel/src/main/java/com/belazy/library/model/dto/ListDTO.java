package com.belazy.library.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 列表DTO
 * @author tangcp
 */
@Data
public  class ListDTO extends BasicDTO{
    @ApiModelProperty("排序规则：正序-ASC 倒序-DESC")
    private String sort;
    @ApiModelProperty("排序字段名称")
    private String orderField;
}
