package com.xyz.im.service.im;

import com.xyz.im.base.exception.BusinessException;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author Zhu WeiJie
 * @date 2020/8/14
 */
@Slf4j
public class NettyHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    /**
     * 连接集合
     */
    public static ChannelGroup channelGroup;

    /**
     * 若是群 群的连接集合
     */
    public static Map<Long, ChannelGroup> groupMap;

    /**
     * 在线人数
     */
    public static int online;

    static {
        channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    }

    /**
     * 接收到客户都发送的消息
     *
     * @param ctx
     * @param msg
     */
    @Override
    public void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) {
        // 取出消息体 根据消息体信息 决定消息发送
//        MsgBody msgBody = JSON.parseObject(msg.text(), MsgBody.class);
        log.info("收到消息 msg={}", msg.text());

        sendAllMessages(ctx, msg);
    }

    /**
     * 客户端建立连接
     *
     * @param ctx
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        channelGroup.add(ctx.channel());
        online = channelGroup.size();
        // 查出用户所在群 对应群注入客户端连接
        log.info(ctx.channel().remoteAddress() + "上线了!");
    }

    /**
     * 关闭连接
     *
     * @param ctx
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        channelGroup.remove(ctx.channel());
        online = channelGroup.size();
        // 用户所在群 对应群去除客户端连接
        log.info(ctx.channel().remoteAddress() + "断开连接");
    }

    /**
     * 出现异常
     *
     * @param ctx
     * @param cause
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.error("ex", cause);
        throw new BusinessException("服务器异常，请稍后再试");
    }

    /**
     * 给某个人发送消息
     */
    private void sendMessage(ChannelHandlerContext ctx, TextWebSocketFrame msg) {
//        MsgBody msgBody = JSON.parseObject(msg.text(), MsgBody.class);

        ctx.channel().writeAndFlush(msg);
    }

    /**
     * 给每个人发送消息,除发消息人外
     */
    private void sendAllMessages(ChannelHandlerContext ctx, TextWebSocketFrame msg) {
//        MsgBody msgBody = JSON.parseObject(msg.text(), MsgBody.class);

        for (Channel channel : channelGroup) {
            if (!channel.id().asLongText().equals(ctx.channel().id().asLongText())) {
                channel.writeAndFlush("this is a test msg hahahahaha");
            }
        }
    }
}
