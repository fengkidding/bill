package com.bill.socket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * netty通道
 *
 * @author f
 * @date 2019-12-17
 */
public class NettyWebSocketChannelHandler extends ChannelInitializer<SocketChannel> {

    /**
     * 初始化通道
     *
     * @param socketChannel
     * @throws Exception
     */
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline channelPipeline = socketChannel.pipeline();
        channelPipeline.addLast("http-codec", new HttpServerCodec());
        channelPipeline.addLast("aggregator", new HttpObjectAggregator(65536));
        channelPipeline.addLast("http-chunked", new ChunkedWriteHandler());
        channelPipeline.addLast("handler", new NettyWebSocketHandler());
    }
}