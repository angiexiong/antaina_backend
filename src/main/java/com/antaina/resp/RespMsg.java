package com.antaina.resp;

import lombok.Data;

@Data
public class RespMsg<T> {

    private int code;

    private String msg;

    private T data;

}
