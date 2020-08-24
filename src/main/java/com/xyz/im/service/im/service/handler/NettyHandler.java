package com.xyz.im.service.im.service.handler;

import com.alibaba.fastjson.JSON;
import com.xyz.im.base.log.ImLogUtils;
import com.xyz.im.service.im.dto.MsgReqBody;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * netty websocket 处理
 *
 * @author xyz
 * @date 2020/8/14
 */
@Service
@ChannelHandler.Sharable
public class NettyHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    @Resource
    private ChatService chatService;

    /**
     * 接收到客户都发送的消息
     *
     * @param ctx 用户本次连接上下文
     * @param msg 接收的内容
     */
    @Override
    public void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) {
        try {
            // 取出消息体 根据消息体信息 决定消息发送
            MsgReqBody msgReqBody = JSON.parseObject(msg.text(), MsgReqBody.class);
            ImLogUtils.info("收到消息 msg={}", msgReqBody);

            switch (msgReqBody.getMsgType()) {
                case REGISTER:
                    chatService.register(msgReqBody, ctx);
                    break;
                case SEND_GROUP:
                    chatService.groupSend(msgReqBody, ctx);
                    break;
                case SEND_SINGLE:
                    chatService.friendSend(msgReqBody, ctx);
                    break;
                default:
                    MsgSendUtils.sendErrorMessage(ctx, "发送消息有误，请稍后再试");
                    break;
            }
        } catch (Exception e) {
            ImLogUtils.warn("im read msg ex, msg={}", msg.text(), e);
            MsgSendUtils.sendErrorMessage(ctx, "发送消息有误，请稍后再试");
        }
    }

    /**
     * 关闭连接
     *
     * @param ctx 用户本次连接上下文
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        chatService.remove(ctx);
    }

    /**
     * 出现异常
     *
     * @param ctx   用户本次连接上下文
     * @param cause 异常
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ImLogUtils.warn("im exceptionCaught", cause);
        ctx.close();
    }

}
