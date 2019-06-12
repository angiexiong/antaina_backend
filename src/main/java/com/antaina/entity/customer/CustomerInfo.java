package com.antaina.entity.customer;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;

@Data
public class CustomerInfo {

	@Id
	private Long id;

	/**
	 * 客户名称
	 */
	@ApiModelProperty(value = "客户名称", position = 1)
	private String name;

	/**
	 * 优先级(数字越大，优先级越高)
	 */
	@ApiModelProperty(value = "优先级(数字越大，优先级越高)", position = 2)
	private Integer priority;

	private java.util.Date createTime;

	private java.util.Date updateTime;


}
