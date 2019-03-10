package com.redsun.timer;

import java.util.Timer;
import java.util.TimerTask;

public class TestTimerTask extends TimerTask {

    private int count = 0;

    private Timer timer;

    public TestTimerTask(Timer timer, int count) {
        this.timer = timer;
        this.count = count;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ",  count = " + count);
        if (count == 5) {
            System.out.println("异常!");
            throw new RuntimeException("异常!");
        }
        if (count == 9) {
            timer.cancel();
        }
    }
}
