package com.belazy.library.core.basics;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 分页DTO
 * @author tangcp
 */
@Data
public class PageDTO extends ListDTO {
    @ApiModelProperty("页容量")
    private int pageSize;
    @ApiModelProperty("页码偏移量")
    private int pageOffset;
}
