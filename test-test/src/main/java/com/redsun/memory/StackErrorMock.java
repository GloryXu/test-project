package com.redsun.memory;

public class StackErrorMock {
    private static int index = 1;

    public void call() {
        index++;
        call();
    }

    /**
     * 每次stack deep不一样
     *
     * @param args
     */
    public static void main(String[] args) {
        StackErrorMock mock = new StackErrorMock();
        try {
            mock.call();
        } catch (Throwable e) {
            System.out.println("Stack deep : " + index);
            e.printStackTrace();
        }
    }
}
