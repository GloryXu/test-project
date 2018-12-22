package number;

import org.junit.Test;

public class IntegerTest {
    /**
     * 测试Integer自动装箱，拆箱
     */
    @Test
    public void testPackage() {
        Integer a = 12;
        if(a == 12) {
            System.out.println("aaaa");
        }
    }

    @Test
    public void testInteger() {
        Integer a = 11;
        Integer b = 11;

        Integer aa = 1111;
        Integer bb = 1111;

        System.out.println(a == b);
        System.out.println(aa == bb);
    }
}
