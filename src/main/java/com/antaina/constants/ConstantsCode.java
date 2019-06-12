/**
 * Copyright (C), 2018-2018, TOPEX
 * FileName: ConstantsCode
 * Author:   Weidw
 * Date:     2018/11/9 10:43
 * Description: 静态常量编码
 * History:
 * <author>     <time>       <version>        <desc>
 * Weidw        10:43         1.0
 */
package com.antaina.constants;

/**
 * <br>
 * 〈静态常量编码〉
 * <br>
 *
 * @author Weidw
 * @create 2018/11/9
 * @since 1.0.0
 * 错误代码枚举，5位
 */
public class ConstantsCode {
    /**
     * 密码规则正则
     * ^ 匹配一行的开头位置
     * (?![0-9]+$) 预测该位置后面不全是数字
     * (?![a-zA-Z]+$) 预测该位置后面不全是字母
     * [0-9A-Za-z] {8,16} 由8-16位数字或这字母组成
     * $ 匹配行结尾位置
     */
    public static final String PASS_REGEXP = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";



    /**
     * 密码不能为空
     */
    public static final String PASS_EMPTY = "20002";

    /**
     * 确认密码不能为空
     */
    public static final String CONFIRM_PASS_WORD_EMPTY = "20003";

    /**
     * 交易密码不能为空
     */
    public static final String TRADE_PASS_WORD_EMPTY = "20004";

    /**
     * 消息类型不能为空
     */
    public static final String MESSAGE_TYPE_EMPTY = "20005";

    /**
     * 用户账号不能为空
     */
    public static final String USER_ACCOUNT_EMPTY = "20006";

    /**
     * 验证码不能为空
     */
    public static final String CODE_EMPTY = "20007";

    /**
     * 用户账号规则错误
     */
    public static final String USER_ACCOUNT_REGEXP_ERROR = "20008";

    /**
     * 密码规则错误
     */
    public static final String PASS_REGEXP_ERROR = "20009";

    /**
     * 两次输入密码不一致
     */
    public static final int TWO_PWD_NOT_MATCH = 20012;

    /**
     * 新旧密码一致
     */
    public static final int NEW_OLD_PWD_MATCH = 20013;

    /**
     * 用户名称不能为空
     */
    public static final int USERNAME_EMPTY = 20014;

    /**
     * id不能为空
     */
    public static final int ID_EMPTY = 20020;

    /**
     * 联系电话不能为空
     */
    public static final int PHONE_EMPTY = 20025;
}
