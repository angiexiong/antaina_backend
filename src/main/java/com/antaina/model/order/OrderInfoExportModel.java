package com.antaina.model.order;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderInfoExportModel {

    private String orderNo;

    private String customerName;

    private String customerProductCode;

    private String productCode;

    private String productName;

    private String productModel;

    private String productType;

    private String productUnit;

    private java.math.BigDecimal totalAmount;

    private BigDecimal deliveryAmount;

    private BigDecimal remainingAmount;

    private String status;

    private String deliveryDate;

    private java.util.Date createTime;

    private java.util.Date updateTime;
}
