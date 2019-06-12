package com.antaina.enums;

import com.antaina.resp.IRet;
import lombok.Getter;

/**
 * 通用枚举类resp
 */
@Getter
public enum BaseResult implements IRet {

    /**
     * 成功
     */
    SUCCESS(200, "success"),

    /**
     * 失败
     */
    ERROR(500, "error");

    private final int code;

    private final String message;

    BaseResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
