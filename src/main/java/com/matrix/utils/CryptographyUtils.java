package com.matrix.utils;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @author Javen-Liu
 * @version 1.0
 * @date 2020/3/23 22:16
 * @github https://github.com/Javen-Liu
 */
public class CryptographyUtils {
    public static final String SALT = "matrix";
    public static String md5Encryption(String source, String salt){
        return new Md5Hash(source, salt).toString();
    }
}
