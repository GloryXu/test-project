package test.redsun.aes.aes256;

import com.redsun.aes.aes256.AesEncryptAndDecrypt256;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.util.encoders.Hex;
import org.junit.Test;

public class AesEncryptAndDecrypt256Test {

    String key = "1111111111111111111111111111111111111111111111111111111111111111";

    @Test
    public void aes256Encode() throws Exception {
        String str = "glory";
        byte[] result = AesEncryptAndDecrypt256.Aes256Encode(str, Hex.decode(key));
        System.out.println(Base64.encodeBase64String(result));
    }

    @Test
    public void aes256Decode() throws Exception {
        String data = "wK5T0h6NaacZoNwvUV/fLw==";
        String result = AesEncryptAndDecrypt256.Aes256Decode(Base64.decodeBase64(data), Hex.decode(key));
        System.out.println(result);
    }

}