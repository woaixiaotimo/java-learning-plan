package nettyDemo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 服务端
 * Netty4
 * Netty5模型有问题主流使用3/4
 */
public class NettyServer {

    private static final int port = 6789; //设置服务端端口
    private static  EventLoopGroup group = new NioEventLoopGroup();   // 通过nio方式来接收连接和处理连接
    private static  ServerBootstrap serverBootstrap = new ServerBootstrap();

    /**
     * Netty创建全部都是实现自AbstractBootstrap。
     * 客户端的是Bootstrap，服务端的则是    ServerBootstrap。
     **/
    public static void main(String[] args) throws InterruptedException {
        try {

            //绑定时间处理线程池
            serverBootstrap.group(group);
            //绑定通道类型
            serverBootstrap.channel(NioServerSocketChannel.class);
            //设置过滤器
            serverBootstrap.childHandler(new NettyServerFilter());
            // 服务器绑定端口监听
            ChannelFuture f = serverBootstrap.bind(port).sync();
            System.out.println("服务端启动成功...");
            // 监听服务器关闭监听
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully(); ////关闭EventLoopGroup，释放掉所有资源包括创建的线程
        }
    }
}
