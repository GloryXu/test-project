//package com.redsun.aes.aes256;
//
//import org.apache.commons.codec.binary.Hex;
//import org.springframework.util.StringUtils;
//
//import javax.crypto.KeyGenerator;
//import javax.crypto.SecretKey;
//import java.io.UnsupportedEncodingException;
//import java.security.NoSuchAlgorithmException;
//import java.security.SecureRandom;
//import java.util.logging.Logger;
//
///**
// * 网联 AES 密钥加密工具类
// *
// * @author wangbo
// */
//public class AesHelper {
//    private final static Logger logger = LoggerFactory.getLogger(AesHelper.class);
//    private String              charset;
//
//    /**
//     * 商盟上送 AES KEY
//     */
//    private byte[]              aesKeys;
//
//    /**
//     * 创建AES加密对象，内部自动生成 AES 密钥
//     *
//     * @param charset
//     * @return
//     * @throws RuntimeException
//     */
//    public static AesHelper getInstance(String charset) throws RuntimeException {
//        return new AesHelper(charset);
//    }
//
//    /**
//     * 创建AES加密解密对象，AES密钥由外部传人
//     * <p>这里的AES密钥为网联上送的数字信封明文，包含上送方前缀部分，方法自己处理前缀部分
//     *
//     * @param charset
//     * @param epccAesKey 网联平台上送数字信封（ AES 密钥）， 可通过
//     *            {@link #RSAHelper.decryptToAES()}解密AES获取
//     * @return
//     * @throws RuntimeException
//     */
//    public static AesHelper getInstance(String charset, byte[] epccAesKey) throws RuntimeException {
//        return new AesHelper(charset, remove(epccAesKey));
//    }
//
//    /**
//     * 获取数字信封（获取加密后的 AES KEY）
//     *
//     * @return
//     * @throws UnsupportedEncodingException
//     */
//    public String getDgtlEnvlp(RSAHelper rSAHelper) throws RuntimeException {
//        byte[] bytes = ArrayUtils.addAll(EpccDataConstant.PREFIX, aesKeys);
//
//        logger.debug("aes keys to key: {} ", aesKeys);
//        logger.debug("aes keys to bytes: {} ", bytes);
//        return rSAHelper.encrypt(bytes);
//    }
//
//    /**
//     * 网联平台上送 AES 密钥，可通过 {@link #RSAHelper.decryptToAES()}解密AES获取
//     *
//     * @param epccKeySpec
//     */
//    public void setAesKay(byte[] epccAesKey) {
//        this.aesKeys = remove(epccAesKey);
//    }
//
//    /**
//     * 使用AES密钥对关键字加密
//     *
//     * @param content 要加密的关键字
//     * @return 返回密文
//     * @throws RuntimeException
//     */
//    public String encrypt(String content) throws RuntimeException {
//        if (StringUtils.isBlank(content)) {
//            return "";
//        }
//        try {
//            // 创建密码器
//            Cipher cipher = Cipher.getInstance(Mechanism.AES_ECB_PKCS5);
//            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(aesKeys, Mechanism.AES_KEY));
//            //加密
//            byte[] result = cipher.doFinal(content.getBytes(charset));
//
//            logger.debug("aes 256 encrypt : {} ", result);
//
//            //Base64编码
//            return Base64.encodeBase64String(result);
//        } catch (UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException e) {
//            logger.error("AES 加密失败：" + e.getMessage(), e);
//            throw new RuntimeException(e.getMessage());
//        }
//    }
//
//    /**
//     * 使用AES密钥解密密文
//     *
//     * @param ciphertext 密文
//     * @return 返回明文
//     * @throws RuntimeException
//     */
//    public String decrypt(String ciphertext) throws RuntimeException {
//        if (StringUtils.isBlank(ciphertext)) {
//            return "";
//        }
//        try {
//            // 创建密码器
//            Cipher cipher = Cipher.getInstance(Mechanism.AES_ECB_PKCS5);
//            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(aesKeys, Mechanism.AES_KEY));
//            //解密
//            byte[] result = cipher.doFinal(Base64.decodeBase64(ciphertext));
//            logger.debug("aes 265 decrypt : {} ", result);
//            return new String(result, charset);
//        } catch (UnsupportedEncodingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException e) {
//            logger.error("AES 解密失败：" + e.getMessage(), e);
//            throw new RuntimeException(e.getMessage());
//        }
//    }
//
//    private AesHelper(String charset) throws RuntimeException {
//        this.charset = charset;
//        generatorKey();
//
//    }
//
//    private AesHelper(String charset, byte[] epccAesKey) throws RuntimeException {
//        super();
//        this.charset = charset;
//        this.aesKeys = epccAesKey;
//    }
//
//    /**
//     * 生成对称密钥
//     * @throws RuntimeException
//     */
//    private void generatorKey() throws RuntimeException {
//        //"AES"：请求的密钥算法的标准名称
//        KeyGenerator kgen = null;
//        try {
//            kgen = KeyGenerator.getInstance(Mechanism.AES_KEY);
//            ///256：密钥生成参数；securerandom：密钥生成器的随机源, 生成对称AES密钥
//            kgen.init(Mechanism.KEY_INIT_128, new SecureRandom());
//
//            SecretKey secretKey = kgen.generateKey();
//            aesKeys = secretKey.getEncoded();
//
//            String hex = encodeHexString(aesKeys);
//            aesKeys = hex.getBytes(charset);
//
//            logger.debug("generator aes keys: {} ", aesKeys);
//        } catch ( NoSuchAlgorithmException | UnsupportedEncodingException e) {
//            logger.error("创建 AES 密钥失败：" + e.getMessage(), e);
//            throw new RuntimeException(e.getMessage());
//        }
//    }
//
//    public static String encodeHexString(final byte[] data) {
//        return new String(Hex.encodeHex(data, false));
//    }
//
//    public static byte[] remove(byte[] bytes){
//        return ArrayUtils.subarray(bytes, 3, bytes.length);
//    }