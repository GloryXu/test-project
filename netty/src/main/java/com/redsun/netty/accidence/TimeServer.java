package com.redsun.netty.accidence;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class TimeServer {

    public void bind(int port) throws Exception {
        // 配置服务端的NIO线程组
        // 创建两个，一个用于服务端接收客户端的连接，另一个用于进行SocketChannel的网络读写
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            // 用于启动NIO服务端的辅助启动类，目的是降低服务端的开发复杂度
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    // 设置创建的Channel为NioServerSocketChannel，功能对应于JDK NIO类库中的ServerSocketChannel类
                    .channel(NioServerSocketChannel.class)
                    // 配置NioServerSocketChannel的TCP参数，设置backlog为1024
                    .option(ChannelOption.SO_BACKLOG, 1024)

                    // 绑定I/O事件的处理类ChildChannelHandler，作用类似于Reactor模式中的handler类
                    // 主要用于处理网络I/O事件，例如记录日志、对消息进行编解码
                    .childHandler(new ChildChannelHandler());

            // 绑定端口，同步等待成功
            // 功能类似于JDK的java.util.concurrent.Future，主要用于异步操作的通知回调
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();

            // 等待服务端监听端口关闭
            // 等待服务端链路关闭之后main函数才退出
            channelFuture.channel().closeFuture().sync();
        } finally {
            // 优雅退出，释放线程池资源
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    private class ChildChannelHandler extends ChannelInitializer<SocketChannel> {

        @Override
        protected void initChannel(SocketChannel socketChannel) throws Exception {
            socketChannel.pipeline().addLast(new TimeServerHandler());
        }

    }

    public static void main(String[] args) throws Exception {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                // 采用默认值
                e.printStackTrace();
            }
        }
        new TimeServer().bind(port);
    }
}
