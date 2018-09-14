package com.aio.aio1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    //线程池
    private ExecutorService executorService;
    //线程组
    private AsynchronousChannelGroup threadGroup;
    //服务器通道
    public AsynchronousServerSocketChannel assc;


    public Server(int port) {
        try {
            //创建一个带缓存的线程池
            executorService = Executors.newCachedThreadPool();
            //创建线程组
            threadGroup = AsynchronousChannelGroup.withCachedThreadPool(executorService, 1);
            //创建服务器通道
            assc = AsynchronousServerSocketChannel.open(threadGroup);
            //进行绑定
            assc.bind(new InetSocketAddress(port));

            System.out.println("server start port : " + port);

            //进行阻塞
            assc.accept(this, new ServerCompletionHandler());
            Thread.sleep(Integer.MAX_VALUE);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
        }


    }

    public static void main(String[] args) {
        Server server = new Server(8765);
    }


}
