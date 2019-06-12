package com.antaina.entity.storage;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;

@Data
public class StorageDetail {

	@Id
	private Long id;

	/**
	 * 主表库存项id
	 */
	@ApiModelProperty(value = "主表库存项id")
	private Long storageId;

	/**
	 * 月份
	 */
	@ApiModelProperty(value = "主表库存项id")
	private Integer month;

	/**
	 * 月度入库
	 */
	@ApiModelProperty(value = "主表库存项id")
	private java.math.BigDecimal monthlyInput;

	/**
	 * 月度出库
	 */
	@ApiModelProperty(value = "主表库存项id")
	private java.math.BigDecimal monthlyOutput;

	private java.util.Date createTime;

	private java.util.Date updateTime;


}
