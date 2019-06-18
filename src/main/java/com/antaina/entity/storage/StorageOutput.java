package com.antaina.entity.storage;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;

@Data
public class StorageOutput {

	@Id
	private Long id;

	/**
	 * 物料编号
	 */
	@ApiModelProperty(value = "物料编号")
	private String productCode;

	/**
	 * 出库量
	 */
	@ApiModelProperty(value = "出库量")
	private java.math.BigDecimal amount;

	/**
	 * 出库类型(0:生产出库, 1:销售出库, 2:退货出库)
	 */
	@ApiModelProperty(value = "出库类型(0:生产出库, 1:销售出库, 2:退货出库)")
	private Integer type;

	/**
	 * 统计状态(0:未统计, 1:已统计)
	 */
	@ApiModelProperty(value = "统计状态(0:未统计, 1:已统计)")
	private Integer statisticFlag;

	private java.util.Date createTime;

	private java.util.Date updateTime;


}
