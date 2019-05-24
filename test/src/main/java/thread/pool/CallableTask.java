package thread.pool;

import java.util.concurrent.Callable;

/**
 * @author xuguangrong
 * @description
 * @date Created at 13:06 2019/5/13
 */
public class CallableTask implements Runnable {

    private int i;

    public CallableTask(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        if (i == 3) {
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("CallableTask-------------" + i);
    }
}
