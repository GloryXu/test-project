package com.redsun.des;

import org.apache.commons.codec.binary.Hex;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.Key;
import java.security.SecureRandom;

public class DesEcbDemo {
    private final static String DES = "DES/ECB/NoPadding";
    private final static String ENCODE = "GBK";
//    private final static String defaultKey = "test1234";//
    private final static String defaultKey = "test1234";//

    public static void main(String[] args) throws Exception {
//        String data = "sdfsdfss";// -->5544ed742978d5bc
        String data = "sdfsdfss11111111111111111111111111111111";// -->5544ed742978d5bc 9d38b654481e11bf
        System.out.println("密文：" + encrypt(data));
        System.out.println("明文：" + decrypt(encrypt(data)));

    }

    /**
     * 使用 默认key 加密
     *
     * @return String
     * @author lifq
     * @date 2015-3-17 下午02:46:43
     */
    public static String encrypt(String data) throws Exception {
        byte[] bt = encrypt(data.getBytes(ENCODE), defaultKey.getBytes(ENCODE));
        return Hex.encodeHexString(bt);
    }

    /**
     * 使用 默认key 解密
     *
     * @return String
     * @author lifq
     * @date 2015-3-17 下午02:49:52
     */
    public static String decrypt(String data) throws IOException, Exception {
        byte[] bt = decrypt(Hex.decodeHex(data.toCharArray()), defaultKey.getBytes(ENCODE));
        return new String(bt, ENCODE);
    }

    /**
     * Description 根据键值进行加密
     *
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        // 创建一个DESKeySpec对象
        SecretKeySpec spec = new SecretKeySpec(key, "DES");

        Cipher cipher = Cipher.getInstance(DES);
        // 实例化cipher
        cipher.init(Cipher.ENCRYPT_MODE, spec);

        return cipher.doFinal(data);
    }

    /**
     * Description 根据键值进行解密
     *
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        SecretKeySpec spec = new SecretKeySpec(key, "DES");
        Cipher cipher = Cipher.getInstance(DES);
        cipher.init(Cipher.DECRYPT_MODE, spec);
        return cipher.doFinal(data);
    }
}
