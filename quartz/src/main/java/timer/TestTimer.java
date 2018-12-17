package timer;

import java.util.Timer;

public class TestTimer {

    public static void main(String args[]) throws InterruptedException {
        Timer timer = new Timer();

        TestTimerTask testTimerTask = null;
        for (int i = 0; i < 10; i++) {
            testTimerTask = new TestTimerTask(timer, i);
            timer.schedule(testTimerTask, (3 + i) * 1000);
        }

        Thread.sleep(100*1000);
    }

}
