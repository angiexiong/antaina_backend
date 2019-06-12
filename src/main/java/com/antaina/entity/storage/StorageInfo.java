package com.antaina.entity.storage;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;

@Data
public class StorageInfo {

	@Id
	private Long id;

	/**
	 * 历史总库存
	 */
	@ApiModelProperty(value = "历史总库存")
	private java.math.BigDecimal totalAmount;

	/**
	 * 年度总库存
	 */
	@ApiModelProperty(value = "年度总库存")
	private java.math.BigDecimal yearAmount;

	/**
	 * 年份
	 */
	@ApiModelProperty(value = "年份")
	private Integer year;

	private java.util.Date createTime;

	private java.util.Date updateTime;


}
