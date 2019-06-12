package com.antaina.model;

import lombok.Data;

/**
 * @author lp
 * @date: 2018/12/7 17:39
 * @description:
 */
@Data
public class LoginToken {

    /**
     * 用户id(列id)
     */
    private Long userId;

    /**
     * 用户账号
     */
    private String userAccount;

    /**
     * 类别  1. exe  2. app
     */
    private Integer type;

    public LoginToken() {
    }

    public LoginToken(Long userId, String userAccount, Integer type) {
        this.userId = userId;
        this.userAccount = userAccount;
        this.type = type;
    }

}
