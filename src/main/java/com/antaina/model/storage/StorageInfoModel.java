package com.antaina.model.storage;


import lombok.Data;

@Data
public class StorageInfoModel {

	private Long id;

	/**
	 * 物料编号
	 */
	private String productCode;

	/**
	 * 历史总库存
	 */
	private java.math.BigDecimal totalAmount;

	/**
	 * 年度总库存
	 */
	private java.math.BigDecimal yearAmount;

	/**
	 * 年份
	 */
	private Integer year;


}
