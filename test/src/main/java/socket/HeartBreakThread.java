package socket;

import java.io.IOException;
import java.net.Socket;

/**
 * @filename HeartBreakThread.java
 * @comment heartbreak testing thread, used to keep socket connect alive.
 * @author Martin
 * 
 */
public class HeartBreakThread implements Runnable {
	/**
	 * client socket
	 */
	private Socket request;

	/**
	 * a flag used to identify whether to keep connect alive.
	 */
	private boolean isKeepAlive = true;

	public HeartBreakThread(Socket request) {
		this.request = request;
	}

	@Override
	public void run() {
		while (isKeepAlive) {
			try {
				
				// write heart break info.
				SocketUtil.writeStr2Stream("Heart break",
						request.getOutputStream());
				// the sleeping time is less than server's settimeout time.
				Thread.sleep(3000);
				
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("HeartBreaking end.");
	}

	public Socket getRequest() {
		return request;
	}

	public void setRequest(Socket request) {
		this.request = request;
	}

	public boolean isKeepAlive() {
		return isKeepAlive;
	}

	public void setKeepAlive(boolean isKeepAlive) {
		this.isKeepAlive = isKeepAlive;
	}

}
