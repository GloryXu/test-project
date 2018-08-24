package com.redsun.aes.aes256;

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;

public class AES256ECB {

    public static void main(String[] args) throws Exception{
        byte[] key = generate(256);
//        byte[] key = generate(128);
        System.out.println("key = " + Hex.encodeHexString(key));// 32 * 8 = 256bit
//        String content = "xugr";
        String content = "xugrxugr";
        System.out.println("明文：" + content);

        byte[] encryptResult = encrypt(content, key);
        System.out.println("密文：" + Hex.encodeHexString(encryptResult));

        String decryptResult = decrypt(encryptResult, key);
        System.out.println("解密：" + decryptResult);
    }

    public static byte[] generate(int bits) throws Exception{
        //"AES"：请求的密钥算法的标准名称
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        //256：密钥生成参数；securerandom：密钥生成器的随机源
        SecureRandom securerandom = new SecureRandom();
        kgen.init(bits, securerandom);
        //生成秘密（对称）密钥
        SecretKey secretKey = kgen.generateKey();
        //返回基本编码格式的密钥
        byte[] enCodeFormat = secretKey.getEncoded();
        return enCodeFormat;
    }

    public static byte[] encrypt(String content, byte[] keys) {
        try {
            // 根据给定的字节数组构造一个密钥。enCodeFormat：密钥内容；"AES"：与给定的密钥内容相关联的密钥算法的名称
            SecretKeySpec key = new SecretKeySpec(keys, "AES");
            // 将提供程序添加到下一个可用位置
            Security.addProvider(new BouncyCastleProvider());
            // 创建一个实现指定转换的 Cipher对象，该转换由指定的提供程序提供。
            // "AES/ECB/PKCS7Padding"：转换的名称；"BC"：提供程序的名称
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding", "BC");

            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] byteContent = content.getBytes("utf-8");
            byte[] cryptograph = cipher.doFinal(byteContent);
            return Base64.encode(cryptograph);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decrypt(byte[] cryptograph, byte[] keys) {
        try {
            SecretKeySpec key = new SecretKeySpec(keys, "AES");
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding", "BC");

            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] content = cipher.doFinal(Base64.decode(cryptograph));
            return new String(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
