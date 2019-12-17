package com.bill.socket;

import com.bill.common.log.LogBackUtils;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * netty配置
 *
 * @author f
 * @date 2019-12-17
 */
public class NettyConfig {

    /**
     * 正在使用的netty连接通道
     */
    private static ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    public static void main(String[] a) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workGroup);
            serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap.childHandler(new WebSocketChannelHandler());
            Channel ch = serverBootstrap.bind(10050).sync().channel();
            ch.closeFuture().sync();
        } catch (Exception e) {
            LogBackUtils.error("netty启动异常：", e);
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    public static void addGroup(Channel channel) {
        group.add(channel);
    }

    public static void removeGroup(Channel channel) {
        group.remove(channel);
    }

    public static ChannelGroup getGroup() {
        return group;
    }

    private NettyConfig() {
    }
}
