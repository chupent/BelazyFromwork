package com.belazy.library.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 分页DTO
 * @author tangcp
 */
@Data
public class PageDTO extends ListDTO {
    @ApiModelProperty("页容量(页条数)")
    private long size =10;
    @ApiModelProperty("页码偏移量(页码)")
    private long offset=1;
}
