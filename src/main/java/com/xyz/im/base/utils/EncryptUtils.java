package com.xyz.im.base.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtils {

    private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    /**
     * 将字节数组转换为16进制的字符串
     *
     * @param byteArray 字节数组
     * @return 16进制的字符串
     */
    private static String byteArrayToHexString(byte[] byteArray) {
        StringBuilder sb = new StringBuilder();
        for (byte byt : byteArray) {
            sb.append(byteToHexString(byt));
        }
        return sb.toString();
    }

    /**
     * 将字节转换为16进制字符串
     *
     * @param byt 字节
     * @return 16进制字符串
     */
    private static String byteToHexString(byte byt) {
        int n = byt;
        if (n < 0) {
            n = 256 + n;
        }
        return hexDigits[n / 16] + hexDigits[n % 16];
    }

    /**
     * 将摘要信息转换为相应的编码
     *
     * @param code    编码类型
     * @param message 摘要信息
     * @return 相应的编码字符串
     */
    private static String encode(String code, String message) {
        MessageDigest md;
        String encode = null;
        try {
            md = MessageDigest.getInstance(code);
            encode = byteArrayToHexString(md.digest(message
                    .getBytes()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return encode;
    }

    /**
     * 将摘要信息转换成MD5编码
     *
     * @param message 摘要信息
     * @return MD5编码之后的字符串
     */
    public static String md5Encode(String message) {
        return encode("MD5", message);
    }

}
