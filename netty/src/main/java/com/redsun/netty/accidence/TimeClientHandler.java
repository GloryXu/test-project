package com.redsun.netty.accidence;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.logging.Logger;

public class TimeClientHandler extends ChannelHandlerAdapter {

    private static final Logger logger = Logger.getLogger(TimeClientHandler.class.getName());

    private final ByteBuf firstMessage;

    public TimeClientHandler() {
        byte[] req = "QUERY TIME ORDER".getBytes();
        firstMessage = Unpooled.buffer(req.length);
        firstMessage.writeBytes(req);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable cause)
            throws Exception {
        // 释放资源
        logger.warning("Unexpected exception from downstream : " + cause.getMessage());
        channelHandlerContext.close();
    }

    /**
     * 当客户端和服务端TCP链路建立成功之后，Netty的NIO线程会调用channelActive方法
     */
    @Override
    public void channelActive(ChannelHandlerContext channelHandlerContext) throws Exception {
        channelHandlerContext.writeAndFlush(firstMessage);
    }

    @Override
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req, "UTF-8");
        logger.info("Now is : " + body);
    }

}
