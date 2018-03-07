package socket;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Date;

/**
 * filename:SocketUtil.java
 * author:����
 * comment:
 */

/**
 * @author martin
 * 
 */
public class SocketUtil {

	/**
	 * write string 2 a outputstream
	 * 
	 * @param str
	 *            to write string
	 * @param out
	 *            stream
	 * @throws IOException 
	 */
	public static void writeStr2Stream(String str, OutputStream out) throws IOException {
		try {
			System.out.println(SocketUtil.getNowTime() + ": prepared to write [" + str + "].");
			// add buffered writer
			BufferedOutputStream writer = new BufferedOutputStream(out);

			// write
			writer.write(str.getBytes());
			
			writer.flush();
		} catch (IOException ex) {
			//System.out.println(SocketUtil.getNowTime() + ex);
			throw ex;
		}
	}

	/**
	 * read string from a inputstream
	 * 
	 * @param in
	 * @return
	 * @throws IOException 
	 */
	public static String readStrFromStream(InputStream in) throws IOException {
		//System.out.println(getNowTime() + " : start to read string from stream");
		StringBuffer result = new StringBuffer("");

		// build buffered reader
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));

		// read 1024 bytes per time
		char[] chars = new char[2048];
		int len;

		try {
			while ((len = reader.read(chars)) != -1) {
				// if the length of array is 1M
				if (2048 == len) {
					//then append all chars of the array
					result.append(chars);
				} 
				// if the length of array is less then 1M
				else {
					//then append the valid chars
					for (int i = 0; i < len; i++) {
						result.append(chars[i]);
					}
					break;
				}
			}

		} catch (IOException e) {
			System.out.println(e);
			throw e;
		}
		//System.out.println("end reading string from stream");
		return result.toString();
	}
	
	public static String getNowTime()
	{
		return new Date().toString();
	}

}
