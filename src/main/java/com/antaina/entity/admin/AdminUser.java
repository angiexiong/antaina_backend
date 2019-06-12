package com.antaina.entity.admin;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.LinkedList;
import java.util.List;

@Data
public class AdminUser {

	@Id
	private Long id;

	/**
	 * 用户名
	 */
	private String useraccount;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 联系电话
	 */
	private String phone;

	/**
	 * 状态（0:删除,1:正常,2冻结登陆...）
	 */
	private Integer status;

	/**
	 * 用户访问权限
	 */
	private String accessPermission;

	/**
	 * 用户访问菜单
	 */
	private String accessMenu;

	private java.util.Date createTime;

	private java.util.Date updateTime;

	@Transient
	@ApiModelProperty(value="分割后的访问权限列表")
	private List<String> permissionList = new LinkedList();

	@Transient
	@ApiModelProperty(value="分割后的访问菜单列表")
	private List<String> menuList = new LinkedList();
}
