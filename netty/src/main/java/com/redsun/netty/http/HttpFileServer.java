package com.redsun.netty.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

public class HttpFileServer {

    private static final String DEFAULT_URL = "/netty/src/main/java/com/redsun/netty";

    public void run(final int port, final String url) throws Exception {
        // 配置服务端NIO线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast("http-decoder", new HttpRequestDecoder());
                            /*
                             * 将多个消息转换为单一的FullHttpRequest或者FullHttpResponse
                             * 原因是 HTTP解码器在每个HTTP消息中会生成多个消息对象
                             * （1）HttpRequest/HttpResponse
                             * （2）HttpContent
                             * （3）LastHttpContent
                             */
                            ch.pipeline().addLast("http-aggregator", new HttpObjectAggregator(65536));
                            ch.pipeline().addLast("http-encoder", new HttpResponseEncoder());
                            /*
                             * 作用是支持异步发送大的码流（例如大的文件传输），但不占用过多的内存
                             * 防止发生Java内存溢出错误
                             */
                            ch.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
                            /*
                             * 用于文件服务器的业务逻辑处理
                             */
                            ch.pipeline().addLast("fileServerHandler", new HttpFileServerHandler(url));
                        }
                    });

            // 绑定端口，同步等待成功
            ChannelFuture channelFuture = serverBootstrap.bind("127.0.0.1", port).sync();
            System.out.println("HTTP 文件目录服务器启动，网址是：" + "http://127.0.0.1:" + port + url);

            // 等待服务器监听端口关闭
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 优雅退出，释放线程池资源
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
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
        String url = DEFAULT_URL;
        if (args.length > 1)
            url = args[1];
        new HttpFileServer().run(port, url);
    }
}
