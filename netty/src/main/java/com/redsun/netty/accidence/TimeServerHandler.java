package com.redsun.netty.accidence;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.Date;
import java.util.logging.Logger;

/**
 * 对于网络事件进行读写操作
 *
 * @author xugr
 */
public class TimeServerHandler extends ChannelHandlerAdapter {

    private static final Logger logger = Logger.getLogger(TimeServerHandler.class.getName());

    @Override
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception {
        // ByteBuf类似于JDK中的java.nio.ByteBuffer对象
        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];// 根据可读的字节创建byte数组
        buf.readBytes(req);// 将缓冲区中的字节数组复制到新建的byte数组中
        String body = new String(req, "UTF-8");
        logger.info("The time server receive order : " + body);
        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
        channelHandlerContext.write(resp);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable cause) throws Exception {
        channelHandlerContext.close();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext channelHandlerContext) throws Exception {
        // 将消息发送队列中的消息写入到SocketChannel中发送给对方
        /**
         * 为了防止频繁地唤醒Selector进行消息发送，Netty的write方法并不直接将消息写入SocketChannel中，调用write
         * 方法只是把待发送的消息放到发送缓冲数组中，再调用flush方法，将发送缓冲区的消息全部写到SocketChannel中
         */
        channelHandlerContext.flush();
    }

}
