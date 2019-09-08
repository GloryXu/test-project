package threadlocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xuguangrong
 * @description 测试线程变量
 *
 * 测试失败！！！
 *
 * @date Created at 23:40 2019/8/20
 */
public class ThreadLocalMain {

    static ExecutorService executorService = Executors.newSingleThreadExecutor();



    public static void main(String[] args) {
        final int shouldGcNum = 2;// 应该发生gc的数字

        ThreadLocal<Boolean> tl1 = new ThreadLocal<>();

        for (int i = 0;i<4;i++) {
            final int tmp = i;
            executorService.execute(() -> {
                if (tmp == 0) {
                    tl1.set(Boolean.TRUE);
                }

                System.out.println("第" + tmp +  "次，值为" + tl1.get());
            });

            if (i == shouldGcNum) {
                System.gc();
            }
        }
    }

}
