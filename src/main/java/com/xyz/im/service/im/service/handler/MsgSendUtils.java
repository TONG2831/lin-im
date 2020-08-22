package com.xyz.im.service.im.service.handler;

import com.alibaba.fastjson.JSON;
import com.xyz.im.base.common.ActionStatus;
import com.xyz.im.base.common.BizActionStatus;
import com.xyz.im.base.handler.returnn.JsonResult;
import com.xyz.im.service.im.dto.MsgRespBody;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

/**
 * 消息发送
 *
 * @author xyz
 * @date 2020-08-22
 */
public class MsgSendUtils {

    /**
     * 发送消息
     *
     * @param ctx         用户连接上下文
     * @param msgRespBody 返回消息体
     */
    public static void sendMessage(ChannelHandlerContext ctx, MsgRespBody msgRespBody) {
        JsonResult jsonResult = JsonResult.builder(ActionStatus.OK.getValue(), ActionStatus.OK.getReason());
        jsonResult.setData(msgRespBody);
        String message = JSON.toJSONString(jsonResult);
        ctx.channel().writeAndFlush(new TextWebSocketFrame(message));
    }

    /**
     * 异常发送消息
     *
     * @param ctx      用户连接上下文
     * @param errorMsg 错误消息
     */
    public static void sendErrorMessage(ChannelHandlerContext ctx, String errorMsg) {
        JsonResult jsonResult = JsonResult.builder(BizActionStatus.IM_EX.getValue(), errorMsg);
        String message = JSON.toJSONString(jsonResult);
        ctx.channel().writeAndFlush(new TextWebSocketFrame(message));
    }

}
