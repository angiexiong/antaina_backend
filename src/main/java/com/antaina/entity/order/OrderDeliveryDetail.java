package com.antaina.entity.order;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;

@Data
public class OrderDeliveryDetail {

    @Id
    private Long id;

    @ApiModelProperty(value = "订单id")
    private Long orderId;

    @ApiModelProperty(value = "出货量")
    private BigDecimal amount;

    private java.util.Date createTime;

    private java.util.Date updateTime;
}
