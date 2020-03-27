package com.matrix.utils;

/**
 * @author Javen-Liu
 * @version 1.0
 * @date 2020/3/27 13:11
 * @github https://github.com/Javen-Liu
 * 字符串处理工具类
 */
public class StringUtils {
    public static boolean isNotEmpty(String str) {
        return str != null && !"".equals(str.trim());
    }
}
