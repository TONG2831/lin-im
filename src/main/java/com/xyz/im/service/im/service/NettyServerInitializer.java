package com.xyz.im.service.im.service;

import com.xyz.im.service.im.service.handler.NettyHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * netty server 初始化
 *
 * @author xyz
 * @date 2020/8/14
 */
@Service
public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {

    @Resource
    private NettyHandler nettyHandler;

    @Override
    protected void initChannel(SocketChannel socketChannel) {
        ChannelPipeline pipeline = socketChannel.pipeline();
        //以下三个是Http的支持
        //http解码器
        pipeline.addLast(new HttpServerCodec());
        //支持写大数据流
        pipeline.addLast(new ChunkedWriteHandler());
        //http聚合器
        pipeline.addLast(new HttpObjectAggregator(1024 * 62));
        //websocket支持,设置路由
        pipeline.addLast(new WebSocketServerProtocolHandler("/lx"));
        //添加自定义的助手类
        pipeline.addLast(nettyHandler);
    }
}
