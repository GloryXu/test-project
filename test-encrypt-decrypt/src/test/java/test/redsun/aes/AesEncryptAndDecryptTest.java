package test.redsun.aes;

import com.redsun.aes.AesEncryptAndDecrypt;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.junit.Assert;
import org.junit.Test;

public class AesEncryptAndDecryptTest {

    String key = "1111111111111111111111111111111111111111111111111111111111111111";

    @Test
    public void aesEncrypt() throws Exception {
        String data = "glory";
        byte[] result = AesEncryptAndDecrypt.aesEncrypt(data.getBytes(), Hex.decodeHex(key.toCharArray()));

        Assert.assertEquals("Q0OZicxMT8DsLYfF7NhifQ==", Base64.encodeBase64String(result));
    }

    @Test
    public void aesDecrypt() throws Exception {
        String data = "Q0OZicxMT8DsLYfF7NhifQ==";
        byte[] result = AesEncryptAndDecrypt.aesDecrypt(Base64.decodeBase64(data), Hex.decodeHex(key.toCharArray()));
        Assert.assertEquals("glory", new String(result));
    }

}