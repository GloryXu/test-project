package io.stream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

/**
 * @author xuguangrong
 * @description 字节流读取测试
 * @date Created at 10:52 2019/1/28
 */
public class ByteArrayInputStreamMain {
    public static void main(String[] args) throws UnsupportedEncodingException {
        ByteArrayInputStream bai = new ByteArrayInputStream("hahahhaha".getBytes("UTF-8"));
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[4];
        int count;
        while((count = bai.read(data, 0, 2)) != -1) {
            outStream.write(data, 0, count);
        }

        System.out.println(new String(outStream.toByteArray(), "UTF-8"));
    }
}
