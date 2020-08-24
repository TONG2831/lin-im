package com.xyz.im.service.im.service;

import com.xyz.im.base.log.ImLogUtils;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * Netty WebSocket服务器
 * 使用独立的线程端口
 *
 * @author xyz
 * @date 2020/8/14
 */
@Component
public class NettyServer {

    @Value("${netty.port}")
    private int port;

    @Resource
    private ThreadPoolTaskExecutor nettyExecutor;

    @Resource
    private NettyServerInitializer nettyServerInitializer;

    /**
     * q启动netty服务
     */
    @PostConstruct
    public void start() {
        nettyExecutor.execute(() -> {
            ImLogUtils.info("netty websocket start ");
            //创建主线程组，接收请求
            EventLoopGroup bossGroup = new NioEventLoopGroup();
            //创建从线程组，处理主线程组分配下来的io操作
            EventLoopGroup workerGroup = new NioEventLoopGroup();
            //创建netty服务器
            try {
                ServerBootstrap serverBootstrap = new ServerBootstrap();
                serverBootstrap.group(bossGroup, workerGroup)//设置主从线程组
                        .channel(NioServerSocketChannel.class)//设置通道
                        .childHandler(nettyServerInitializer);//子处理器，用于处理workerGroup中的操作
                //启动server
                ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
                //监听关闭channel
                channelFuture.channel().closeFuture().sync();
            } catch (Exception e) {
                ImLogUtils.warn("netty websocket ex", e);
            } finally {
                //关闭主线程
                bossGroup.shutdownGracefully();
                //关闭从线程
                workerGroup.shutdownGracefully();
            }
        });
    }

    /**
     * 关闭netty服务
     */
    @PreDestroy
    public void close() {
        ImLogUtils.error("netty websocket close");
        nettyExecutor.shutdown();
    }

}
