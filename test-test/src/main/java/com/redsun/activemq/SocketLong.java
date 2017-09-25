package com.redsun.activemq;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * C/S架构的服务端对象。
 * <p>
 *
 * @author xugr
 * @since 1.0
 */
public class SocketLong {

    private int port;

    public SocketLong() {
    }

    public SocketLong(int port) {
        this.port = port;
    }

    public void start() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            Socket socket = serverSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void init() {
        SocketLong socketLong = new SocketLong();
        socketLong.start();
    }

}
