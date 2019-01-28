package thread;

/**
 * @author xuguangrong
 * @description 测试类
 * @date Created at 18:29 2019/1/28
 */
public class NoVisiabilityTest {
    private static class ReadThread extends Thread {

        private volatile boolean ready;
        private int number;

        @Override
        public void run() {
            while (!ready) {
                number++;
            }
            System.out.println(ready);
        }

        public void readOn() {
            this.ready = true;
        }
    }


    public static void main(String rags[]) throws InterruptedException {
        ReadThread readThread = new ReadThread();
        readThread.start();
        Thread.sleep(200);
        readThread.readOn();
        System.out.println("=======" + readThread.ready);

    }
}
