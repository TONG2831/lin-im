package com.xyz.im.domain;

import lombok.Data;

/**
 * 群表
 *
 * @author xyz
 * @date 2020/8/21
 */
@Data
public class Group {
    private Long id;

    /**
     * 群名称
     */
    private String groupName;

    /**
     * 群头像
     */
    private String groupAvatar;

    /**
     * 添加时间
     */
    private Long createTime;

    /**
     * 修改时间
     */
    private Long updateTime;
}