package com.antaina.model.storage;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class StorageOutputModel {

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
}
