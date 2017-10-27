package com.redsun.md5;

import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;

public class MD5HashingExample {

    public static void main(String[] args) throws Exception {
        String password = "123456";
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte byteData[] = md.digest();
        System.out.println("Digest(in hex format):: " + Hex.encodeHexString(byteData));
    }
}
