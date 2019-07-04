package com.antaina.model.storage;

import com.antaina.entity.storage.RptStorage;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class RptStorageExportModel {

    private String customerName;

    private String productCode;

    private String productName;

    private String productModel;

    private String productType;

    private String productUnit;

    private BigDecimal totalAmount;

    private java.math.BigDecimal inputAmount;

    private java.math.BigDecimal outputAmount;

    private String type;

    private java.util.Date createTime;

    private java.util.Date updateTime;
}
