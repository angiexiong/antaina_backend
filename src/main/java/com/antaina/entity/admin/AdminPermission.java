package com.antaina.entity.admin;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;

@Data
public class AdminPermission {

	/**
	 * 主键id
	 */
	@ApiModelProperty(value = "主键id", position = 0)
	@Id
	private Long id;

	/**
	 * 接口url
	 */
	@ApiModelProperty(value = "接口url", position = 1)
	private String url;

	/**
	 * 接口权限名
	 */
	@ApiModelProperty(value = "接口权限名", position = 2)
	private String permission;


}
