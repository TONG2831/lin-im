package com.xyz.im.domain;

import lombok.Data;

/**
 * @author  Zhu WeiJie
 * @date  2020/8/20
 */
/**
    * 用户表
    */
@Data
public class User {
    private Long id;

    /**
    * 用户id
    */
    private Long uid;

    /**
    * 昵称
    */
    private String nickname;

    /**
    * 头像
    */
    private String avatar;

    /**
    * 性别 M/W
    */
    private String sex;

    /**
    * 添加时间
    */
    private Long createTime;

    /**
    * 修改时间
    */
    private Long updateTime;
}