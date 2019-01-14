package threadpool.cache;

/**
 * @author xuguangrong
 * @description 线程执行内容
 * @date Created at 17:57 2018/12/29
 */
public class WorkHandler implements Runnable {

    private String xxx;

    public WorkHandler(String xxx) {
        this.xxx = xxx;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(200000);
            System.out.println(Thread.currentThread().getName() + " start, xxx = " + xxx);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
