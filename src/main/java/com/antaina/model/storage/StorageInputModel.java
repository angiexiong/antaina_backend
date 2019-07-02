package com.antaina.model.storage;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class StorageInputModel {

	private Long id;

	/**
	 * 订单编号
	 */
	@ApiModelProperty(value = "订单编号")
	private String orderNo;

	/**
	 * 物料编号
	 */
	@ApiModelProperty(value = "物料编号")
	private String productCode;

	/**
	 * 入库量
	 */
	@ApiModelProperty(value = "入库量")
	private java.math.BigDecimal amount;

	/**
	 * 入库类型(0:采购入库, 1:生产入库, 2:退货入库)
	 */
	@ApiModelProperty(value = "入库类型(0:采购入库, 1:生产入库, 2:退货入库)")
	private Integer type;
}
