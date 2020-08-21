package com.xyz.im.domain;

import lombok.Data;

/**
 * 群成员表
 *
 * @author xyz
 * @date 2020/8/21
 */
@Data
public class GroupMember {
    private Long id;

    /**
     * 用户id
     */
    private Long uid;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 群id
     */
    private Long groupId;

    /**
     * 成员身份 0:普通成员 1:群主
     */
    private Byte identity;

    /**
     * 添加时间
     */
    private Long createTime;

    /**
     * 修改时间
     */
    private Long updateTime;
}