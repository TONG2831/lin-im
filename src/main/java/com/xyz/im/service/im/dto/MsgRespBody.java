package com.xyz.im.service.im.dto;

import com.xyz.im.service.im.enums.MsgType;
import lombok.Builder;
import lombok.Data;

/**
 * 消息发送返回体
 *
 * @author xyz
 * @date 2020-08-22
 */
@Data
@Builder
public class MsgRespBody {

    /**
     * 消息类型 REGISTER:上线 SEND_SINGLE:单对单发送消息 SEND_GROUP:群发送消息
     */
    private MsgType msgType;

    /**
     * 消息内容
     */
    private String msg;

    /**
     * 用户id
     */
    private String from;

    /**
     * 发送对象id
     */
    private String to;

}
