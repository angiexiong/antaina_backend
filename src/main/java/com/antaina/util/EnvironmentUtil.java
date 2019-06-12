package com.antaina.util;

import org.springframework.core.env.Environment;

/**
 * properties获取方法
 * Date: 2018/12/17 10:46
 * Description:
 *
 * @author
 */
public class EnvironmentUtil {

    public static String get(String key) {
        Environment environment = ApplicationContextUtil.get(Environment.class);
        return environment.getProperty(key);
    }
}
