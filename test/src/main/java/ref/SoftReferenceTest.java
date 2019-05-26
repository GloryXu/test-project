package ref;

import java.lang.ref.SoftReference;

/**
 * @author xuguangrong
 * @description 软引用测试
 * @date Created at 23:23 2019/3/17
 */
public class SoftReferenceTest {

    public static void main(String[] args) {
        SoftReference ref = new SoftReference(new TestObj());
        System.gc();
        System.out.println(ref.get());

        doSomething();

        System.gc();

        System.out.println(ref.get());

    }

    public static void doSomething() {
        String[] array = new String[1024 * 10];
        for(int i = 0; i < 1024 * 10; i++) {
            for(int j = 'a'; j <= 'z'; j++) {
                array[i] += (char)j;
            }
        }
    }

    static class TestObj {
        private String a;
        TestObj() {
            a = "llll";
        }

        @Override
        public String toString() {
            return "TestObj{" +
                    "a='" + a + '\'' +
                    '}';
        }
    }
}
