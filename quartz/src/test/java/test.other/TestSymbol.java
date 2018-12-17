package test.other;

import org.junit.Test;

public class TestSymbol {

    @Test
    public void test() {
        int count = 27;
        // 取反减1
        System.out.println(~count);


        int count2 = 29;
        System.out.println(-1 << count2);
        System.out.println(count2 >> 1);
        System.out.println(0 << count2);
        System.out.println(1 << count2);
        System.out.println(2 << count2);
        System.out.println(3 << count2);
    }

    @Test
    public void test2() {
        int i = 1;
        System.out.println("开始执行");
        retry:
        for (;;) {
            System.out.println("哈哈哈");
            if (i == 2) {
                break retry;
            }
            i++;
        }
        System.out.println("到这了！");
    }
}
