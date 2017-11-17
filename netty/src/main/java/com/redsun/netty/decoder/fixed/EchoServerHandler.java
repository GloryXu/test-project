package com.redsun.netty.decoder.fixed;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * 利用FixedLengthFrameDecoder解码器，无论一次接受到多少数据包，它都会按照构造函数中设置的固定长度进行解码，
 * 如果是半包消息，FixedLengthFrameDecoder会缓存半包消息并等待下个包到达后进行拼包，知道读取到一个完整的包
 */
@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelHandlerAdapter  {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Receive client : [" + msg + "]");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();// 发生异常，关闭链路
    }
}
