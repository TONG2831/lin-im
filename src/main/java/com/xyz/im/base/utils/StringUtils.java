package com.xyz.im.base.utils;

import java.util.Arrays;
import java.util.List;

public class StringUtils {

    /**
     * 按传入正则分割字符串并存入list
     *
     * @param content
     * @param regex
     * @return
     */
    public static List<String> splitToList(String content, String regex) {
        return Arrays.asList(content.split(regex));
    }

    /**
     * 首字母大写
     *
     * @param content
     * @return
     */
    public static String toFirstUpper(String content) {
        content = content.substring(0, 1).toUpperCase() + content.substring(1);
        return content;
    }

}
