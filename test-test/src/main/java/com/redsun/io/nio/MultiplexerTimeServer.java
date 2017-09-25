package com.redsun.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class MultiplexerTimeServer implements Runnable {

    private Selector selector;// 多路复用器

    private ServerSocketChannel servChannel;

    // volatile告诉jvm， 它所修饰的变量不保留拷贝，直接访问主内存(避免了多线程操作同一变量引起的问题)
    private volatile boolean stop;

    /**
     * 初始化多路复用器、绑定端口
     *
     * @param port
     */
    public MultiplexerTimeServer(int port) {
        try {
            selector = Selector.open();
            servChannel = ServerSocketChannel.open();
            servChannel.configureBlocking(false);// 设置为异步非阻塞模式
            servChannel.socket().bind(new InetSocketAddress(port), 1024);
            servChannel.register(selector, SelectionKey.OP_ACCEPT);// 将ServerSocketChannel注册到Selector上 ，监听SelectionKey.OP_ACCEPT操作位
            System.out.println("The time server is start in port:" + port);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void stop() {
        this.stop = true;
    }

    public void run() {
        // 循环遍历Selector
        while (!stop) {
            try {
                // 休眠时间为1s，也有无参的select方法。当有处于就绪状态的Channel时，selector将会返回就绪状态的Channel的selectionKey集合，通过对就绪状态的channel
                // 集合进行迭代，可以进行网络的异步读写操作
                selector.select(1000);
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = selectedKeys.iterator();
                SelectionKey key = null;
                while (it.hasNext()) {
                    key = it.next();
                    it.remove();
                    try {
                        handleInput(key);
                    } catch (Exception e) {
                        if (key != null) {
                            key.cancel();
                            if (key.channel() != null) {
                                key.channel().close();
                            }
                        }
                    }
                }
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }

        // 多路复用器关闭后，所有注册在上面的Channel和Pipe等资源都会被自动去注册并关闭，所以不需要重复释放资源
        if (selector != null) {
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleInput(SelectionKey key) throws IOException {
        if (key.isValid()) {
            // 处理新接入的请求消息，根据SelectionKey的操作位进行判断即可获知网络事件的类型
            if (key.isAcceptable()) {
                // Accept the new connection
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                SocketChannel sc = ssc.accept();// TCP物理链路正式建立
                sc.configureBlocking(false);// 将新创建的SocketChannel设置为异步非阻塞
                // add the new Connection to the selector
                sc.register(selector, SelectionKey.OP_READ);
            }
            if (key.isReadable()) {
                // Read the Data
                SocketChannel sc = (SocketChannel) key.channel();
                // 分配一个1024大小的字节缓冲区
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                // 读取请求码流
                int readBytes = sc.read(readBuffer);
                if (readBytes > 0) {
                    //作用是：将缓冲区当前的limit设置为position，position设置为0，用于后续对缓冲区的读取操作
                    readBuffer.flip();
                    byte[] bytes = new byte[readBuffer.remaining()];
                    // 将缓冲区中可读的字节数组复制到新创建的字节数组中
                    readBuffer.get(bytes);
                    String body = new String(bytes, "UTF-8");
                    System.out.println("The time server receive order:" + body);
                    String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new java.util.Date(System.currentTimeMillis()).toString() : "BAD ORDER";
                    doWrite(sc, currentTime);
                } else if (readBytes < 0) {// 链路已经关闭，需要关闭SocketChannel，释放资源
                    // 对端链路关闭
                    key.cancel();
                    sc.close();
                } else {
                    // 读到0个字节，忽略
                }
            }
        }
    }

    private void doWrite(SocketChannel channel, String response) throws IOException {
        if (response != null && response.trim().length() > 0) {
            byte[] bytes = response.getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);// 分配这么大的一个字节缓冲区
            // 将字节数组放置到缓冲区中
            writeBuffer.put(bytes);
            writeBuffer.flip();// flip:轻击，反转
            channel.write(writeBuffer);
        }

    }

}
