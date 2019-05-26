package face.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 有一个压测场景需要三个线程加载完毕资源后再同时发起对目标服务的压测请用java代码实现。
 *
 * @author xuguangrong
 * @description 面试
 */
public class AntFaceTest {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        CountDownLatch latch = new CountDownLatch(3);

        SourceLoad w1 = new SourceLoad(latch, "资源1");
        SourceLoad w2 = new SourceLoad(latch, "资源2");
        SourceLoad w3 = new SourceLoad(latch, "资源3");

        PressureTest pressureTest = new PressureTest(latch);

        executor.execute(w3);
        executor.execute(w2);
        executor.execute(w1);
        executor.execute(pressureTest);

        executor.shutdown();
    }
}
