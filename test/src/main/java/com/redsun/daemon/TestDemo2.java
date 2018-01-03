package com.redsun.daemon;

import java.io.*;


class TestRunnable implements Runnable {

    public void run() {
        try {
            Thread.sleep(1000);//守护线程阻塞1秒后运行
            File f = new File("daemon.txt");
            FileOutputStream os = new FileOutputStream(f, true);
            os.write("daemon".getBytes());
            System.out.println("-------------------------------");
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
    }
}

public class TestDemo2 {
    public static void main(String[] args) throws InterruptedException {
        Runnable tr=new TestRunnable();
        Thread thread=new Thread(tr);
        thread.setDaemon(true); //设置守护线程
        thread.start(); //开始执行分进程
    }
}
