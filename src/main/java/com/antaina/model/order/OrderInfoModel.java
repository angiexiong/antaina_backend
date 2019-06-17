package com.antaina.model.order;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class OrderInfoModel {

	private Long id;

	/**
	 * 客户编号
	 */
	@ApiModelProperty(value = "客户编号")
	private Long customerId;

	/**
	 * 客户物料编号
	 */
	@ApiModelProperty(value = "客户物料编号")
	private String customerProductCode;

	/**
	 * 订单量
	 */
	@ApiModelProperty(value = "订单量")
	private java.math.BigDecimal amount;

	/**
	 * 物料编号
	 */
	@ApiModelProperty(value = "物料编号")
	private String productCode;

	/**
	 * 交货日期
	 */
	@ApiModelProperty(value = "交货日期")
	private String deliveryDate;

}
