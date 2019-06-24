package exception;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

@Slf4j
public class ExceptionTest {

    @Test
    public void testTryCatch() {
        log.info("--------------result = {}", method());
    }

    private int method() {
        try {
            log.info("try");

            // This returning code will be not executed.
            return 1;
        } catch (Exception e) {
            log.info("catch");
            return 2;
        } finally {
            log.info("finally");
            return 3;
        }
    }

    @Test
    public void testExceptionToString() {
        try {
            String str = null;

            str.substring(0);
        }catch (Exception e) {
            System.out.println(e.toString());
            System.out.println(e);
        }
    }

    @Test
    public void testCheckException () {
        byte[] bytes = "haha".getBytes();

        try {
            System.out.println(new String(bytes, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCacheEception() {
        String str = null;

        /*try {
            System.out.println(str.toString());
        } finally {
            System.out.println("1");
        }*/

        try {
            System.out.println(str.toString());
        } catch (Exception e) {
            System.out.println("3");
        } finally {
            System.out.println("1");
        }
        System.out.println("2");
    }

}
