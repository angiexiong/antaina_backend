package com.antaina.entity.storage;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;

@Data
public class RptStorage {

	@Id
	private Long id;

	/**
	 * 物料编号
	 */
	@ApiModelProperty(value = "物料编号")
	private String productCode;

	/**
	 * 客户编号
	 */
	@ApiModelProperty(value = "客户编号")
	private Long customerId;

	/**
	 * 入库物料总量
	 */
	@ApiModelProperty(value = "入库物料总量")
	private java.math.BigDecimal inputAmount;

	/**
	 * 出库物料总量
	 */
	@ApiModelProperty(value = "出库物料总量")
	private java.math.BigDecimal outputAmount;

	/**
	 * 统计类型/统计频率(0:每小时, 1:每天, 2:每周,3:每月,4:每年)
	 */
	@ApiModelProperty(value = "统计类型/统计频率(0:每小时, 1:每天, 2:每周,3:每月,4:每年)")
	private Integer type;

	private java.util.Date createTime;

	private java.util.Date updateTime;


}
