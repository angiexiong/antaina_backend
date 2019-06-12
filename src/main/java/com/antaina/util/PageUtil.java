package com.antaina.util;

import com.github.pagehelper.PageInfo;

import java.util.List;

public class PageUtil {


    @SuppressWarnings("all")
    public static PageInfo create(List<?> list) {
        if (!(list instanceof List)) {
            throw new RuntimeException("Only support java.util.List!");
        }
        return new PageInfo(list);
    }

}
