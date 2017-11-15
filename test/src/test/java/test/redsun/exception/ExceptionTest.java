package test.redsun.exception;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

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

}
