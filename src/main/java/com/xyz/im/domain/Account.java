package com.xyz.im.domain;

import lombok.Data;

/**
 * @author  Zhu WeiJie
 * @date  2020/8/20
 */

/**
 * 账号表
 */
@Data
public class Account {
    private Long id;

    /**
     * 账号
     */
    private String account;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String mail;

    /**
     * 密码 加密
     */
    private String password;

    /**
     * 添加时间
     */
    private Long createTime;

    /**
     * 修改时间
     */
    private Long updateTime;
}