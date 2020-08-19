package com.xyz.im.service.im;

import lombok.Data;

/**
 * @author Zhu WeiJie
 * @date 2020/8/17
 */
@Data
public class MsgBody {

    private String from;

    /**
     * 消息类型 0:个人 1:群
     */
    private int msgType;

    private String to;

    private String msg;
}
