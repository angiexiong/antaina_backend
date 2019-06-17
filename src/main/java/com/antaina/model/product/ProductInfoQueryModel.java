package com.antaina.model.product;


import com.antaina.entity.product.ProductInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ProductInfoQueryModel extends ProductInfo {
	/**
	 * 客户名称
	 */
	@ApiModelProperty(value = "客户名称")
	private String customerName;

}
