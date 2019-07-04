package com.antaina.model.product;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductInfoExportModel {

    private String customerName;

    private String customerProductCode;

    private String productCode;

    private String productName;

    private String productModel;

    private String productType;

    private String productUnit;

    private BigDecimal totalAmount;

    private java.util.Date createTime;

    private java.util.Date updateTime;
}
