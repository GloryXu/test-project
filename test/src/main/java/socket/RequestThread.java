package socket;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.Executors;

/**
 * filename:RequestThread.java
 * author:martin
 * comment:a thread sending request to socket server. 
 */

/**
 * @author Martin
 * 
 */
public class RequestThread implements Runnable {
	// id
	private int id;
	
	/**
	 * keep connection online or not. default value : false.
	 */
	private boolean isLongConnection = false;

	public RequestThread(int id) {
		this.id = id;
	}

	public RequestThread(int id, boolean isLongConnection) {
		this.id = id;
		this.isLongConnection = isLongConnection;
	}
	
	@Override
	public void run() {
		Socket request = null;
		try {
			// connect to socket server
			request = new Socket("127.0.0.1", 39998);
			
			//start a heart break thread to keep connect online
			HeartBreakThread heartBreaker = new HeartBreakThread(request);
			
			//if keep online, run heart break thread.
			if (isLongConnection)
			{
				Executors.newCachedThreadPool().execute(heartBreaker);
			}
			
			for (int counter = 0; counter < 3; counter ++) {
				// write response info
				SocketUtil.writeStr2Stream("RequestID[" + id + "]this is client. Times[" + counter + "]",
						request.getOutputStream());

				// get info from request when getting a socket request
				String reqStr = SocketUtil.readStrFromStream(request
						.getInputStream());

				System.out.println(SocketUtil.getNowTime() + ":RequestID[" + id + "]get returnmsg [" + reqStr + "].");
				
				if (counter < 1)
				{
					Thread.sleep(2000);
				}
				else
				{
					//sleep 6 seconds to verify hreatbreakthread is ok or not. 
					Thread.sleep(6000);
				}
			}
			
			//if keep online, run heart break thread.
			if (isLongConnection)
			{
				//stop heart breaking.
				heartBreaker.setKeepAlive(false);
			}
			
			//sleep 3s to make sure heart break thread stop rightly.
			Thread.sleep(3000);
			System.out.println("Client end.");
		} catch (IOException e) {
			System.out.println("Request[" + id + "] get a exception : " + e);
		} catch (Throwable e) {
			System.out.println("Request[" + id + "] get a exception : " + e);
		} finally {
			if (request != null) {
				try {
					//request.getInputStream().close();
					//request.getOutputStream().close();
					request.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public boolean isLongConnection() {
		return isLongConnection;
	}

	public void setLongConnection(boolean isLongConnection) {
		this.isLongConnection = isLongConnection;
	}

}
