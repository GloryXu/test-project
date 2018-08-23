package com.redsun.sha;

import org.apache.commons.codec.binary.Hex;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha1Example {

    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String str = "123456";
        MessageDigest md = MessageDigest.getInstance("SHA1");
        md.update(str.getBytes("UTF-8"));
        byte[] byteData = md.digest();
        // 输出160 bit
        System.out.println("Digest(in hex format):: " + Hex.encodeHexString(byteData));
    }
}
