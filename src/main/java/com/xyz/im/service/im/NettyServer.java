package com.xyz.im.service.im;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author Zhu WeiJie
 * @date 2020/8/14
 */
@Component
@Slf4j
public class NettyServer {

    @PostConstruct
    public void start() throws InterruptedException {
        log.info("netty server start ");
        //创建主线程组，接收请求
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //创建从线程组，处理主线程组分配下来的io操作
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        //创建netty服务器
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)//设置主从线程组
                    .channel(NioServerSocketChannel.class)//设置通道
                    .childHandler(new NettyServerInitializer());//子处理器，用于处理workerGroup中的操作
            //启动server
            ChannelFuture channelFuture = serverBootstrap.bind(8081).sync();
            //监听关闭channel
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();//关闭主线程
            workerGroup.shutdownGracefully();//关闭从线程
        }
    }

}
