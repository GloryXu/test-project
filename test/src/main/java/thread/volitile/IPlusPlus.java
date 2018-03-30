package thread.volitile;

public class IPlusPlus {
    private static volatile int j;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                j++;
            }
        });
        thread.start();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                j++;
            }
        });
        thread1.start();
        thread.join();
        thread1.join();
        System.out.println("最后的结果是i = " + j);
    }
}
