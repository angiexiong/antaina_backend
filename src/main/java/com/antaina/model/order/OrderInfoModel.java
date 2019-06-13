package com.antaina.model.order;


import lombok.Data;

@Data
public class OrderInfoModel {

	private Long id;

	/**
	 * 客户编号
	 */
	private Long customerId;

	/**
	 * 客户物料编号
	 */
	private String customerProductCode;

	/**
	 * 订单量
	 */
	private java.math.BigDecimal amount;

	/**
	 * 物料编号
	 */
	private String productCode;


}
