package number;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

/**
 * @author xuguangrong
 * @description 字节相关测试类
 * @date Created at 14:58 2019/2/18
 */
public class ByteTest {

    @Test
    public void testByteToString() throws DecoderException, UnsupportedEncodingException {
        // 30317CC79F59C6EF82C600D04D8A74586902DB2ECCD54FCD7CA3483B1DA0951639EE2B
        byte[] arrByte = new byte[]{23,23,23,23,23};

//        System.out.println(Hex.encodeHex(arrByte));

        byte[] a = Hex.decodeHex("30317C C79F59C6EF82C600D04D8A74586902DB2ECCD54FCD7CA3483B1DA0951639EE2B".toCharArray());

        System.out.println(new String(a, "ISO-8859-1").length());
        System.out.println(new String(a, "ISO-8859-1"));

    }
}
