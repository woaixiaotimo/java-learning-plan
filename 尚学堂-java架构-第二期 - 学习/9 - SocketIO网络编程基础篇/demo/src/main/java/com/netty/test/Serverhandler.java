package com.netty.test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.nio.Buffer;
import java.nio.ByteBuffer;

public class Serverhandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        ((ByteBuf) msg).release();

        ByteBuf byteBuffer = (ByteBuf) msg;
        byte[] data = new byte[byteBuffer.readableBytes()];
        byteBuffer.readBytes(data);
        String request = new String(data, "utf-8");
        System.out.println("Server: " + request);

        //发送给客户端
        String response = "我是响应的内容";
//        ctx.write(Unpooled.copiedBuffer(response.getBytes()));
//        ctx.flush();
        ctx.writeAndFlush(Unpooled.copiedBuffer(response.getBytes())).
                addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
