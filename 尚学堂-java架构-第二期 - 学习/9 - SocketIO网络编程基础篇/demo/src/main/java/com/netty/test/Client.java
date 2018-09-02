package com.netty.test;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

public class Client {
    public static void main(String[] args) throws InterruptedException {

        EventLoopGroup group = new NioEventLoopGroup();


        Bootstrap b = new Bootstrap();
        b.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        //设置特殊字符分隔符
                        ByteBuf byteBuf = Unpooled.copiedBuffer("$_".getBytes());
                        socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,byteBuf));
                        //设置字符创形式解码
                        socketChannel.pipeline().addLast(new StringDecoder());
                        //绑定处理类
                        socketChannel.pipeline().addLast(new ClientHandler());
                    }
                });
        ChannelFuture cf1 = b.connect("127.0.0.1", 8765).sync();

        //buf
        cf1.channel().writeAndFlush(Unpooled.copiedBuffer("111$_".getBytes()));
        cf1.channel().writeAndFlush(Unpooled.copiedBuffer("222$_".getBytes()));
        cf1.channel().writeAndFlush(Unpooled.copiedBuffer("333$_".getBytes()));


        cf1.channel().closeFuture().sync();
        group.shutdownGracefully();


    }
}
