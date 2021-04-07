package com.belazy.business.consumer.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author tangcp
 */
@Data
@AllArgsConstructor
public class UserEntity {
    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "年龄")
    private String age;
}
