package com.antaina.model.storage;


import com.antaina.entity.storage.StorageInput;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class StorageInputQueryModel extends StorageInput {

	/**
	 * 物料名
	 */
	@ApiModelProperty(value = "物料名")
	private String productName;

	/**
	 * 物料型号
	 */
	@ApiModelProperty(value = "物料型号")
	private String productModel;

	/**
	 * 物料类型
	 */
	@ApiModelProperty(value = "物料类型")
	private Integer productType;

	/**
	 * 物料单位
	 */
	@ApiModelProperty(value = "物料单位")
	private Integer productUnit;

	/**
	 * 客户名称
	 */
	@ApiModelProperty(value = "客户名称")
	private String customerName;
}
