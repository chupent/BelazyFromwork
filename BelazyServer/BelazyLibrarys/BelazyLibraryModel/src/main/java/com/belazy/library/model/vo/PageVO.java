package com.belazy.library.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * @author tangcp
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class PageVO<T> implements Serializable {
    @ApiModelProperty("数据列表")
    private List<T> records;
    @ApiModelProperty("总条数")
    private Long total;
    @ApiModelProperty("页容量")
    private Long size;
    @ApiModelProperty("页码偏移量")
    private Long offset;
}
