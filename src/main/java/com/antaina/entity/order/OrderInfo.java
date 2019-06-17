package com.antaina.entity.order;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;

@Data
public class OrderInfo {

	@Id
	private Long id;

	/**
	 * 客户编号
	 */
	@ApiModelProperty(value = "客户编号")
	private Long customerId;

	/**
	 * 订单量
	 */
	@ApiModelProperty(value = "订单量")
	private java.math.BigDecimal amount;

	/**
	 * 物料id
	 */
	@ApiModelProperty(value = "物料编号")
	private String productCode;

	@ApiModelProperty(value = "已出货量")
	private BigDecimal deliveryAmount;

	@ApiModelProperty(value = "未出货量")
	private BigDecimal remainingAmount;

	@ApiModelProperty(value = "状态(0:未全部交货, 1:已完成)")
	private Integer status;

	/**
	 * 交货日期
	 */
	@ApiModelProperty(value = "交货日期")
	private String deliveryDate;

	private java.util.Date createTime;

	private java.util.Date updateTime;


}
