package com.antaina.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BaseModel {

    /**
     * 默认从1开始
     */
    @ApiModelProperty(value = "页码", example = "1")
    private Integer pageNum = 1;

    /**
     * 默认单页数据量为10
     */
    @ApiModelProperty(value = "单页数量", example = "10")
    private Integer pageSize = 10;
}
