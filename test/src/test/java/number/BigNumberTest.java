package number;

import java.math.BigDecimal;
import java.math.BigInteger;

public class BigNumberTest {

	private static String lPad(String now ,int expectLength, char paddingChar){
		if(now == null || now.length() >= expectLength)
			return now;
		StringBuilder buf = new StringBuilder(expectLength);
		for(int i = 0,paddingLength = expectLength-now.length();i<paddingLength;i++){
			buf.append(paddingChar);
		}
		return buf.append(now).toString();
	}
	
	public static void main(String[] args) {
		// 这个数字 long是放不下的
		BigDecimal bigDecimal = new BigDecimal("1212121121212121212121212121212121212121212121212121");
		System.out.println("数字的原始值是:" + bigDecimal);
		
//		bigDecimal = bigDecimal.add(BigDecimal.TEN);
//		System.out.println("添加10以后:" + bigDecimal);
		
		// 二进制数字
		byte[] bytes = bigDecimal.toBigInteger().toByteArray();
		for(byte b : bytes){
			String bitString = lPad(Integer.toBinaryString(b & 0xff) ,8, '0');
			System.out.println(bitString);
		}
		//还原结果
		BigInteger bigInteger = new BigInteger(bytes);
		System.out.println("还原结果为:" + bigInteger);
	}
}
