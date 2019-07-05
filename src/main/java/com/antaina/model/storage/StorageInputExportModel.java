package com.antaina.model.storage;

import lombok.Data;

@Data
public class StorageInputExportModel {

    private String customerProductCode;

    private String productCode;

    private String productName;

    private String productModel;

    private String productType;

    private String productUnit;

    private java.math.BigDecimal totalAmount;

    private java.math.BigDecimal amount;

    private String inputType;

    private java.util.Date createTime;

    private java.util.Date updateTime;
}
