package number;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

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
        System.out.println("1".equals(new Integer(1)));
    }

    @Test
    public void testQZ() {
        Map<String, Object> map = new HashMap();

        System.out.println((Integer) map.get("test") == 1);
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

    @Test
    public void testMapPrint() {
        Map<String, Object> map = new HashMap<>();
        Double number = 10000000D;
        map.put("number", number);

        System.out.println(map.toString());

        DataPool inputDataArea = new DataPool("layout");
        inputDataArea.setIdatas(map);
        System.out.println(inputDataArea.toString());
    }

    @Test
    public void testAA () {
        String value = null;
        System.out.println(value = new String("0"));
    }

}
