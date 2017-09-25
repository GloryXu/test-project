package com.redsun.io.aio;

import java.io.IOException;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;

/**
 * 时间服务器处理类
 * 
 * @author xugr
 *
 */
public class AsyncTimeServerHandler implements Runnable {

	private int port;
	
	CountDownLatch latch;
	AsynchronousServerSocketChannel asynchronousServerSocketChannel;
	
	public AsyncTimeServerHandler(int port) {
		this.port = port;
		try {
			asynchronousServerSocketChannel = AsynchronousServerSocketChannel.open();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
	
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
}
