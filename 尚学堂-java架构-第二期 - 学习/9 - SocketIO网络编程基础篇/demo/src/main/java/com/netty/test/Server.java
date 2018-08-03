package com.netty.test;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

public class Server {

    public static void main(String[] args) throws InterruptedException {
        //1、第一个线程组适用于接收Client连接的
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //2、第二个线程组是用于业务操作的
        EventLoopGroup workGroup = new NioEventLoopGroup();
        //3、创建一个辅助类，就是对我们的Server端进行一些列的配置
        ServerBootstrap b = new ServerBootstrap();
        //把两个工作线程组加入进来
        b.group(bossGroup, workGroup)
                //指定使用NioServerSocket通道
                .channel(NioServerSocketChannel.class)
                //一定要使用childHandler，去绑定具体的事件处理器
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        //设置特殊字符分隔符
                        ByteBuf byteBuf = Unpooled.copiedBuffer("$_".getBytes());
                        socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,byteBuf));
                        //设置字符创形式解码
                        socketChannel.pipeline().addLast(new StringDecoder());
                        //绑定处理类


                        ChannelPipeline channelPipeline = socketChannel.pipeline().addLast(new Serverhandler());
                        System.out.println("channelPipeline = " + channelPipeline);
                    }
                })
                //服务器TCP内核模块维护两个队列，称之为AB
                //客户端向服务器connect的时候，会发送带有SYN标志的包(第一次握手)
                //服务器收到客户端发来的SYN时，向客户端发送SYN ACK确认(第二次握手)
                //此时TCP内核模块把客户端连接从A队列移到B队列中，连接完成，应用程序的accept会返回
                //也就是说accept从队列中取出，完成第三次握手的连接
                //A队列和B队列的长度之和是backlog，当A，b两队列之和大于backlog时，新连接会被TCP内核拒绝
                //所以如果backlog过小，可能会出现accept速度跟不上，AB 队列满了导致新的客户端无法连接
                //注意的是;backlog对程序支持的连接数并无影响，backlog影响的只是还没被取出的连接
                //这里相当于就是设置tcp连接的缓冲
                .option(ChannelOption.SO_BACKLOG, 128)
                .option(ChannelOption.SO_SNDBUF,32*1024)//设置发送缓冲大小
                .option(ChannelOption.SO_RCVBUF,32*1024)//设置接收缓冲大小
                //心跳，探活机制
                .childOption(ChannelOption.SO_KEEPALIVE, true);
        //绑定端口进行监听
        ChannelFuture future = b.bind(8765).sync();

        //阻塞server
        future.channel().closeFuture().sync();
        //关闭线程池
        bossGroup.shutdownGracefully();
        workGroup.shutdownGracefully();


    }

}
