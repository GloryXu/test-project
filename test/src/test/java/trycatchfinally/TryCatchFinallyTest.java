package trycatchfinally;

import org.junit.Test;

public class TryCatchFinallyTest {
    @Test
    public void testReturn() {
//        System.out.printf("returnInTry result is %s", returnInTry());
//        System.out.printf("returnInTry1 result is %s", returnInTry1());
//        System.out.printf("returnInTry2 result is %s", returnInTry2());
        System.out.printf("returnInFinally result is %s", returnInFinally());
    }

    private String returnInTry() {
        try {
            String str = "xugr";
            return str;
        } catch (Exception e) {
            System.out.println("in catch");
        } finally {
            System.out.println("in finally");
        }
        System.out.println("after finally");
        return null;
    }

    private String returnInTry1() {
        try {
            String str = "xugr";
            return str + " hah";
        } catch (Exception e) {
            System.out.println("in catch");
        } finally {
            System.out.println("in finally");
        }
        System.out.println("after finally");
        return null;
    }

    private String returnInTry2() {
        String str = null;
        try {
            str = "xugr";
            return str;
        } catch (Exception e) {
            System.out.println("in catch");
        } finally {
            System.out.println("in finally");
            str+="hahhddddddddddddd";
        }
        System.out.println("after finally");
        return null;
    }

    private String returnInFinally() {
        String str = null;
        try {
            str = "xugr";
            return str+"222222";
        } catch (Exception e) {
            System.out.println("in catch");
        } finally {
            System.out.println("in finally");
            str+="hahhddddddddddddd";
            return str;
        }
    }
}
