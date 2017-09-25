package com.redsun.socket;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * filename:SocketServer.java
 * author:martin
 * comment:socketserver
 */

/**
 * @author martin
 * 
 */
public class SocketClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// thread executor
		ExecutorService executor = Executors.newCachedThreadPool();

		// short connection
		executor.execute(new RequestThread(0, false));
		
		// long connection
		executor.execute(new RequestThread(1, true));
	}

}
