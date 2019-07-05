package com.antaina.model.order;

import com.antaina.entity.order.OrderInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class OrderInfoQueryModel extends OrderInfo {

    /**
     * 物料编号
     */
    @ApiModelProperty(value = "物料编号")
    private String productCode;

    /**
     * 客户名
     */
    @ApiModelProperty(value = "客户名")
    private String customerName;

    /**
     * 物料名
     */
    @ApiModelProperty(value = "物料名")
    private String productName;

    /**
     * 物料型号
     */
    @ApiModelProperty(value = "物料型号")
    private String productModel;
}
