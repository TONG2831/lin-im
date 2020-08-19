package com.xyz.im.base.utils;

public class RegexUtils {

    public static boolean checkMobile(String param) {
        String regex = "^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|([6][5,6])|([7][0-8])|([8][0-9])|([9][1,8,9]))[0-9]{8}$";
        return param.matches(regex);
    }

    public static boolean checkMail(String param){
        String regex = "^([A-Za-z0-9_+-.])+@([A-Za-z0-9\\-.])+\\.([A-Za-z]{2,22})$";
        return param.matches(regex);
    }

}
