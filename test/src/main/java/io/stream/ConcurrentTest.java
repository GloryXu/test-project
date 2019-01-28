package io.stream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author xuguangrong
 * @description 并发测试
 * @date Created at 16:25 2019/1/28
 */
public class ConcurrentTest {

    private static ConcurrentHashMap<String, InputStream> map = new ConcurrentHashMap<>();

    static {
        for (int i = 0;i < 10; i++) {
            map.put(Integer.toString(i), new ByteArrayInputStream(UUID.randomUUID().toString().getBytes()));
        }
    }

    public static synchronized InputStream getStream(String key) {
        InputStream inputStream = map.get(key);

        try {
            inputStream.reset();
        } catch (Exception e) {

        }
        return inputStream;
    }

    public static void main(String[] args) throws IOException {
        Executor executor = Executors.newFixedThreadPool(100);

        for (int i = 0;i<1000;i++) {
            String key = Integer.toString(i % 10);
            executor.execute(() -> {
                InputStream inputStream = ConcurrentTest.getStream(key);
                // do something
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                // 读取InputStream中的数据
                try {
                    ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                    byte[] data = new byte[256];
                    int count = -1;
                    while ((count = inputStream.read(data, 0, 128)) != -1){
                        outStream.write(data, 0, count);
                    }
                    data = null;
                    System.out.println(Thread.currentThread().getName() + ", key = " + key + ", value = " + new String(outStream.toByteArray()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        System.in.read();
    }

}
