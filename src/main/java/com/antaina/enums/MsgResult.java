package com.antaina.enums;

import com.antaina.resp.IRet;
import lombok.Getter;

@Getter
public enum MsgResult implements IRet {

    /**
     * ;
     * token空校验
     */
    TOKEN_INVALID(10000, "token失效，请重新登录"),
    /**
     * ;
     * token空校验
     */
    TOKEN_EMPTY(10001, "token不能为空"),

    /**
     * 两次密码不一致！
     */
    PASSWORD_INCONSISTENT(10002, "两次密码不一致!"),

    /**
     * 验证码错误！
     */
    VERCODE_ERROR(10003, "验证码错误!"),

    /**
     * 验证码失效！
     */
    CODE_INVALID(10004, "验证码失效!"),

    /**
     * 注册失败！
     */
    REGISTER_ERROR(10005, "注册失败!"),

    /**
     * 用户名称已被使用！
     */
    USERNAME_ERROR(10006, "用户名称已被使用!"),

    /**
     * 加密因子获取失败！
     */
    KEY_ERROR(10007, "加密因子获取失败!"),

    /**
     * 账号未注册！
     */
    USER_EMPTY(10008, "账号未注册!"),

    /**
     * 密码错误！
     */
    PASSWORD_ERROR(10009, "密码错误!"),

    /**
     * 密码更新失败！
     */
    UPDATE_FAILURE(10010, "密码更新失败!"),

    /**
     * 新密码与旧密码一致！
     */
    PASSWORD_AGREEMENT(10011, "新密码与旧密码一致!"),

    /**
     * 用户登录错误次数超过限制数,需要验证码！
     */
    LOGIN_ERROR(10012, "用户登录错误次数超过限制数,需要验证码!"),

    /**
     * 已被限制登录，请联系客服!
     */
    NOT_LOGIN(10013, "已被限制登录，请联系客服!"),

    /**
     * 加密因子失效！
     */
    KEY_VALID_ERROR(10014, "加密因子失效!"),

    /**
     * 钱包冻结状态，不能交易
     */
    WALLET_LOCK(10015, "钱包冻结状态，不能交易"),

    /**
     * 钱包余额不足，不能交易
     */
    WALLET_BALANCE_NOT_ENOUGH(10016, "钱包余额不足，不能交易"),

    /**
     * 钱包冻结余额不足，不能解冻
     */
    WALLET_BALANCE_LOCK_NOT_ENOUGH(10017, "钱包冻结余额不足，不能解冻"),

    /**
     * 用户锁定状态，不能交易
     */
    USER_TRADE_LOCK(10018, "用户锁定状态，不能交易"),

    /**
     * 用户信息不存在
     */
    USER_NOT_FOUND(10019, "用户信息不存在"),

    /**
     * 账号或密码错误
     */
    USERNAME_OR_PWD_ERROR(10020, "账号或密码错误"),

    /**
     * 账号已冻结或删除
     */
    STATUS_DEL_OR_FREEZE(10021, "账号已冻结或删除"),

    /**
     * 用户id不能为空
     */
    USER_ID_EMPTY(10022, "用户id不能为空"),

    ORDER_EXIST_NO(10023, "订单不存在"),

    DELIVERY_EXIST_NO(10024, "出货记录不存在");

    private final int code;

    private final String message;

    MsgResult(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
