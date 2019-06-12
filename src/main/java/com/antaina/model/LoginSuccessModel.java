/**
 * Copyright (C), 2018-2018, TOPEX
 * FileName: LoginSuccessModel
 * Author:   Weidw
 * Date:     2018/11/22 16:32
 * Description: 用户登录返回参数
 * History:
 * <author>     <time>       <version>        <desc>
 * Weidw        16:32         1.0
 */
package com.antaina.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <br>
 * 〈用户登录返回参数〉
 * <br>
 *
 * @author Weidw
 * @create 2018/11/22
 * @since 1.0.0
 */
@Data
public class LoginSuccessModel {

    @ApiModelProperty(name = "useraccount", value = "admin", example = "admin")
    private String useraccount;
    /**
     * userId
     */
    @ApiModelProperty(name = "userId", value = "userId", example = "143435345345")
    private Long userId;

    /**
     * 用户邮箱
     */
    @ApiModelProperty(name = "email", value = "用户邮箱", example = "dwe123456@11.com")
    private String email;

    /**
     * 用户名
     */
    @ApiModelProperty(name = "username", value = "用户名称", example = "dwe123456")
    private String username;

    /**
     * token
     */
    @ApiModelProperty(name = "token", value = "token", example = "dwe12dfsfsd45dfg3456")
    private String token;

    /**
     * 头像
     */
    @ApiModelProperty(name = "photo", value = "头像", example = "http://dfas/dfasf/4234.img")
    private String photo;

}
