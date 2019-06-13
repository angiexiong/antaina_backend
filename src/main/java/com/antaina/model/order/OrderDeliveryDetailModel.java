package com.antaina.model.order;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDeliveryDetailModel {

    @ApiModelProperty(value = "订单id")
    private Long orderId;

    @ApiModelProperty(value = "出货量")
    private BigDecimal amount;
}
