package threadpool.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xuguangrong
 * @description cacheThreadPool测试
 * @date Created at 17:55 2018/12/29
 */
public class CacheThreadPool {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("111");
        list.add("222");
        list.add("333");
        list.add("444");
        list.add("555");
        list.add("666");

        ExecutorService threadPool = Executors.newCachedThreadPool();

        for (String item : list) {
            threadPool.execute(new WorkHandler(item));
        }

        threadPool.shutdown();
    }
}
