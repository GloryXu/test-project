package io.nio;

public class TimeClient {

    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                // 采用默认值
            }
        }

        new Thread(new TimeClientHandle("127.0.0.1", port), "TimeClient-001").start();

		/*Socket socket = null;
		BufferedReader br = null;
		PrintWriter pw = null;
		try{
			socket = new Socket("127.0.0.1", port);
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			pw = new PrintWriter(socket.getOutputStream(),true);
			pw.println("QUERY TIME ORDER");
			System.out.println("send order 2 server succeed.");
			String resp = br.readLine();
			System.out.println("Now is : "+resp);
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(pw != null){
				pw.close();
				pw = null;
			}
			if(br != null){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				br = null;
			}
			if(socket != null){
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				socket = null;
			}
		}*/
    }
}
