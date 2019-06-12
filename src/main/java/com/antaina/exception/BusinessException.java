package com.antaina.exception;

import com.antaina.enums.BaseResult;
import com.antaina.resp.IRet;

public class BusinessException extends RuntimeException implements IRet {

    private int code;

    private String message;

    public BusinessException() {
    }

    public BusinessException(String message) {
        this.message = message;
        this.code = BaseResult.ERROR.getCode();
    }

    public BusinessException(IRet iRet) {
        this.code = iRet.getCode();
        this.message = iRet.getMessage();
    }

    public BusinessException(int code) {
        this.code = code;
    }

    public BusinessException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
