package com.redsun.aes;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.SecureRandom;

public class AesEncryptAndDecrypt {

    public static void main(String[] args) throws Exception {
//        String data = "xugr";
        String data = "ed1023cd9bf10";
        String key = "11111";

        System.out.println("明文是：" + data + "，加密key为：" + key);

        String cipherText = new String(Hex.encodeHex(aesEncrypt(data.getBytes(), key.getBytes())));
        System.out.println("加密密文是：" + cipherText);

        String plaintext = new String(aesDecrypt(Hex.decodeHex(cipherText.toCharArray()), key.getBytes()));
        System.out.println("解密明文是：" + plaintext);
    }

    /**
     *
     * @param data 加密数据
     * @param key 密码明文
     * @return
     * @throws SecurityException
     */
    static public byte[] aesEncrypt(byte[] data, byte[] key) throws Exception {
        try {
            key = initKey(key, "AES");
            return encrypt(data, key, "AES");
        } catch (Exception e) {
            throw new SecurityException("AES加密异常:"+e.getMessage());
        }
    }

    /**
     *
     * @param data 加密数据
     * @param key 密码明文
     * @return
     * @throws SecurityException
     */
    static public byte[] aesDecrypt(byte[] data, byte[] key) throws SecurityException {
        try {
            key = initKey(key, "AES");
            return decrypt(data, key, "AES");
        } catch (Exception e) {
            throw new SecurityException("AES解密异常:"+e.getMessage());
        }
    }

    /**
     * 根据明文密码生产密钥
     * @param
     * @return
     * @throws Exception
     */
    public static byte[] initKey(byte[] pwd, String algorithm) throws Exception {
        SecureRandom secureRandom;
        if (pwd != null) {
            secureRandom = new SecureRandom(pwd);
        } else {
            secureRandom = new SecureRandom();
        }

        KeyGenerator kg = KeyGenerator.getInstance(algorithm);
        kg.init(secureRandom);

        SecretKey secretKey = kg.generateKey();

        return secretKey.getEncoded();
    }

    public static byte[] encrypt(byte[] data, byte[] key, String algorithm) throws Exception {
        Key k = toKey(key, algorithm);
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, k);
        return cipher.doFinal(data);
    }

    /**
     * ALGORITHM 算法 <br>
     * 可替换为以下任意一种算法，同时key值的size相应改变
     *
     * <pre>
     * DES                  key size must be equal to 56
     * DESede(TripleDES)    key size must be equal to 112 or 168
     * AES                  key size must be equal to 128, 192 or 256,but 192 and 256 bits may not be available
     * Blowfish             key size must be multiple of 8, and can only range from 32 to 448 (inclusive)
     * RC2                  key size must be between 40 and 1024 bits
     * RC4(ARCFOUR)         key size must be between 40 and 1024 bits
     * </pre>
     */
    /**
     * 转换密钥<br>
     *
     * @param key
     * @return
     * @throws Exception
     */
    private static Key toKey(byte[] key, String algorithm) throws Exception {
        if("DES".equalsIgnoreCase(algorithm) || "DESede".equalsIgnoreCase(algorithm)) {
            DESKeySpec dks = new DESKeySpec(key);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(algorithm);
            return keyFactory.generateSecret(dks);
        } else {
            SecretKey secretKey = new SecretKeySpec(key, algorithm);
            return secretKey;
        }
    }

    /**
     * 解密
     *
     * @param data
     * @param key - primary encoding format
     * @return
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data, byte[] key, String algorithm) throws Exception {
        Key k = toKey(key, algorithm);
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, k);
        return cipher.doFinal(data);
    }

}
