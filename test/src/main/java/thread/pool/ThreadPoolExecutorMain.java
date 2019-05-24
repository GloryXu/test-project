package thread.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author xuguangrong
 * @description
 * @date Created at 13:04 2019/5/13
 */
public class ThreadPoolExecutorMain {
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10,500,60,TimeUnit.SECONDS, new SynchronousQueue());

        CallableTask callableTask1 = new CallableTask(1);
        CallableTask callableTask2 = new CallableTask(2);
        CallableTask callableTask3 = new CallableTask(3);
        CallableTask callableTask4 = new CallableTask(4);

        threadPoolExecutor.execute(callableTask1);
        threadPoolExecutor.execute(callableTask2);
        threadPoolExecutor.execute(callableTask3);
        threadPoolExecutor.execute(callableTask4);

        System.out.println("ThreadPoolExecutorMain---------------------");
    }
}
