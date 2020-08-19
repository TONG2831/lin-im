package com.xyz.im.base.enums;

/**
 * 环境参数
 *
 * @author xyz
 * @date 2020/7/2
 */
public enum Profiles {

    /**
     * 开发环境
     */
    DEV("dev"),

    /**
     * 生产环境
     */
    PROD("prod"),
    ;

    private String name;

    Profiles(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
