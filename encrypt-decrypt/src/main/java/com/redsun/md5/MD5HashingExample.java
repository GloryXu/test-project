package com.redsun.md5;

import org.apache.commons.codec.binary.Hex;
import org.junit.Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5HashingExample {

    public static void main(String[] args) throws Exception {
        String msg = "123456";
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(msg.getBytes());
        byte[] byteData = md.digest();
        // 输出128 bit
        System.out.println("Digest(in hex format):: " + Hex.encodeHexString(byteData));
    }

    /*@Test
    public void testDecode() throws DecoderException, NoSuchAlgorithmException {
//        String hexStr = "F002EC55A1EA8865A0677833C8F8462F";
        String hexStr = "2A0923285184943425D1F53DDD58EC7A";
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.digest(Hex.decodeHex(hexStr.toCharArray()));
        String str = new String(Hex.decodeHex(hexStr.toCharArray()));
        System.out.println(str);
    }*/
}
