package com.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;

public class ServerCompletionHandler implements java.nio.channels.CompletionHandler<AsynchronousSocketChannel, Server> {
    @Override
    public void completed(AsynchronousSocketChannel result, Server attachment) {
        //当有下一个客户端介入的时候，直接调用Server的accept方法，这样执行下去保证有多个客户端都可以阻塞
        attachment.assc.accept(attachment, this);
        read(result);
        try {
            InetSocketAddress address =  (InetSocketAddress)result.getLocalAddress();
            System.out.println("address = " + address.getAddress());
            System.out.println("address = " + address.getPort());
            System.out.println("address = " + address.getHostName());
            System.out.println("address = " + address.getHostString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void failed(Throwable exc, Server attachment) {
        exc.printStackTrace();
    }

    private void read(final AsynchronousSocketChannel asc) {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        asc.read(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer resultSize, ByteBuffer attachment) {
                //进行读取之后重置标识位
                attachment.flip();
                //获得读取的字节数
                System.out.println("Server -> " + "收到客户端的数据长度为：" + resultSize);
                //获得读取的数据
                String resultData = new String(attachment.array()).trim();
                System.out.println("Server -> " + "收到客户端的数据信息为：" + resultData);
                String response = new String("服务器响应，收到了客户端发来的数据：" + resultData);
                write(asc, response);
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                exc.printStackTrace();
            }
        });
    }

    private void write(AsynchronousSocketChannel asc, String response) {
        try {
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            byteBuffer.put(response.getBytes());
            byteBuffer.flip();
            asc.write(byteBuffer).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
