/**
 * Copyright (C), 2018-2018, TOPEX
 * FileName: Constants
 * Author:   Weidw
 * Date:     2018/11/13 16:12
 * Description: 常量定义类
 * History:
 * <author>     <time>       <version>        <desc>
 * Weidw        16:12         1.0
 */
package com.antaina.constants;

/**
 * <br>
 * 〈常量定义类〉
 * <br>
 *
 * @author Weidw
 * @create 2018/11/13
 * @since 1.0.0
 */
public class Constants {
    /**
     * 语言简写
     */
    public static final String EN = "en";
    /**
     * 语言简写
     */
    public static final String ZH = "zh";
    /**
     * 是否删除状态 1 删除 0 正常
     */
    public static final int IS_DELETE = 1;
    /**
     * 是否删除状态 1 删除 0 正常
     */
    public static final int IS_NOT_DELETE = 0;
    /**
     * 备注标识前缀
     */
    public static final String MERCHANT_COMMENT = "merchant_comment_";
    /**
     * 商家码标识前缀
     */
    public static final String MERCHANT_TYPE = "merchant_type_";
    /**
     * redis存储key的前缀值：注册验证码
     */
    public static final String REGISTER_PREFIX = "register_code_";

    /**
     * redis存储忘记key的前缀值：登录验证码
     */
    public static final String LOGIN_PREFIX = "login_code_";

    /**
     * redis存储key的前缀值：绑定邮箱
     */
    public static final String BIND_EMAIL_PREFIX = "bind_email_code_";

    /**
     * redis存储修改key的前缀值：重置登录密码验证码
     */
    public static final String RESET_PASS_PREFIX = "reset_pass_code_";

    /**
     * redis存储忘记key的前缀值：忘记登录密码验证码
     */
    public static final String FORGET_PASS_PREFIX = "forget_pass_code_";

    /**
     * redis存储修改key的前缀值：设置交易密码验证码
     */
    public static final String SET_TRADE_PASS_PREFIX = "set_trade_pass_code_";

    /**
     * redis存储忘记key的前缀值：重置交易密码验证码
     */
    public static final String RESET_TRADE_PASS_PREFIX = "reset_trade_pass_code_";

    /**
     * redis存储忘记key的前缀值：提币
     */
    public static final String EXTRACT_PASS_PREFIX = "extract_pass_code_";


    /**
     * redis存储忘记key的前缀值：登录成功
     */
    public static final String LOGIN_SUCCESS_PREFIX = "login_success_";

    /**
     * redis存储忘记key的前缀值：登录成功, 管理端
     */
    public static final String ADMIN_LOGIN_SUCCESS_PREFIX = "admin_login_success_";

    /**
     * redis存储忘记key的前缀值：登录失败
     */
    public static final String LOGIN_ERROR_PREFIX = "login_error_";

    /**
     * 用户加密因子key前缀
     */
    public static final String USER_KEY_PREFIX = "user_key_";

    /**
     * 写死一个对称加密因子
     */
    public static final String AES_RULE = "topex_team_com";

    /*
      token过期时间 s * m, 半个月
     */
    public static final long EXPIRE_TIME = 60 * 60;

    /*
      app token过期时间 s * m   15天
     */
    public static final long EXPIRE_TIME_APP = 60 * 60 * 24 * 15;

    /**
     * 法币代号
     */
    public static final String CURRENCY = "usd";

    /**
     * 前台用户基础等级code
     */
    public static final String FRONT_BASE_LEVEL_CODE = "FRONT_BASE_LEVEL";

    /**
     * 前台用户基础角色code
     */
    public static final String FRONT_BASE_ROLE_CODE = "FRONT_BASE_ROLE";

    /**
     * 成交价格redisKey
     */
    public static final String PRICE_CATEGORY_REDIS_KEY = "transaction_price";

    /**
     * 密码输入错误次数
     */
    public static final String PASSWORD_ERROR_COUNT = "3";

    /**
     * 证件图片存放文件夹名称
     */
    public static final String CERTIFICATES = "certificates";

    /**
     * 做数学运算时的默认精度:8
     */
    public static final int DEFAULT_MATH_PRECISION_8 = 8;
    public static final String DECIMAL_FORMAT_8 = "#.00000000";

    /**
     * 做数学运算时的默认精度:4
     */
    public static final int DEFAULT_MATH_PRECISION_4 = 4;
    public static final String DECIMAL_FORMAT_4 = "#.0000";

    /**
     * 做数学运算时的默认精度:2
     */
    public static final int DEFAULT_MATH_PRECISION_2 = 2;
    public static final String DECIMAL_FORMAT_2 = "#.00";
    public static final String DECIMAL_PREFIX = ".";

    /**
     * 表示系统的钱包id
     * 充值/提现/手续费的时候，用于流水表的targetWalletId
     */
    public static final long SYSTEM_WALLET = 10000;

    public static final String PRIVATE_OSS = "private";
    public static final String PUBLIC_OSS = "public";

    public static final String STRING_SPLITTER_SEMICOLON = ";";

    public static final String STRING_EXCHANGE_RATE_USD = "USD";

}
