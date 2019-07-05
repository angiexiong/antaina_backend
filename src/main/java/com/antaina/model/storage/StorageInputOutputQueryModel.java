package com.antaina.model.storage;


import lombok.Data;

/**
 * 出库入库共用Model
 */
@Data
public class StorageInputOutputQueryModel {

	private Long id;

	private String customerProductCode;

	private java.math.BigDecimal amount;

	private Integer type;

	private java.util.Date createTime;

	private java.util.Date updateTime;


}
