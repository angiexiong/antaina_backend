package com.antaina.model.customer;


import lombok.Data;

@Data
public class CustomerInfoModel {

	private Long id;

	/**
	 * 客户名称
	 */
	private String name;

	/**
	 * 优先级(数字越大，优先级越高)
	 */
	private Integer priority;


}
