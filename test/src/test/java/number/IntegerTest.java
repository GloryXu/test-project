package number;

import org.junit.Test;

public class IntegerTest {
    /**
     * 测试Integer自动装箱，拆箱
     */
    @Test
    public void testPackage() {
        Integer a = 12;
        Integer b = new Integer(12);
        System.out.println("a == 12 result is " + (a == 12));
        System.out.println("a == 12 result is " + (b == 12));
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

    @Test
    public void testMathRount() {
        // round 四舍五入
        System.out.println(Math.round(0.75f));
        System.out.println(Math.round(1.75f));
        System.out.println(Math.round(10.75f));
        System.out.println(Math.round(10.4f));
        System.out.println(Math.round(10.5f));
        System.out.println(Math.round(0.499999f));

        // ceil
        System.out.println(Math.ceil(0.75f));
        System.out.println(Math.ceil(1.75f));
        System.out.println(Math.ceil(10.75f));
        System.out.println(Math.ceil(10.4f));
        System.out.println(Math.ceil(10.5f));
        System.out.println(Math.ceil(0.499999f));
    }

}
