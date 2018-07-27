package com.netty.test;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

public class Server {

    public static void main(String[] args) {
        //1、
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //2、
        EventLoopGroup workGroup = new NioEventLoopGroup();
    }

}
