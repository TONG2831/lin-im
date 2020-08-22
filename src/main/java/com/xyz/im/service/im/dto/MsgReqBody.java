package com.xyz.im.service.im.dto;

import com.xyz.im.service.im.enums.MsgType;
import lombok.Data;

/**
 * 消息发送接收体
 *
 * @author xyz
 * @date 2020/8/17
 */
@Data
public class MsgReqBody {

    /**
     * 用户id
     */
    private String from;

    /**
     * 消息类型 REGISTER:上线 SEND_SINGLE:单对单发送消息 SEND_GROUP:群发送消息
     */
    private MsgType msgType;

    /**
     * 发送对象id 用户id/群ID 上线无需
     */
    private String to;

    /**
     * 消息内容
     */
    private String msg;

}
