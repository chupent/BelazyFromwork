package com.belazy.library.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 基础DTO
 * @author tangcp
 */
@Data
public class BasicDTO implements Serializable {
    @ApiModelProperty("查询过滤内容")
    private String filterContent;
}
