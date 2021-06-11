package com.belazy.library.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 分页DTO
 * @author tangcp
 */
@Data
public class PageDTO extends ListDTO {
    @ApiModelProperty("页容量")
    private Long size;
    @ApiModelProperty("页码偏移量")
    private Long offset;
}
