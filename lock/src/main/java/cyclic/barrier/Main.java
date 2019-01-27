package cyclic.barrier;

import java.io.IOException;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Main {

    static CyclicBarrier c = new CyclicBarrier(3);

    public static void main(String[] args) throws InterruptedException, IOException {
        new Thread(() -> {
            System.out.println("pre print 1");
            try {
                c.await();
                System.out.println("after print 1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            System.out.println("pre print 2");
            try {
                c.await();
                System.out.println("after print 2");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();


        try {
            Thread.sleep(10000);
            c.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println("print 3");

        System.in.read();
    }
}
