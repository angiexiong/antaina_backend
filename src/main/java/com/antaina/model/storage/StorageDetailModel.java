package com.antaina.model.storage;


import lombok.Data;

@Data
public class StorageDetailModel {

	private Long id;

	/**
	 * 主表库存项id
	 */
	private Long storageId;

	/**
	 * 月份
	 */
	private Integer month;

	/**
	 * 月度入库
	 */
	private java.math.BigDecimal monthlyInput;

	/**
	 * 月度出库
	 */
	private java.math.BigDecimal monthlyOutput;


}
