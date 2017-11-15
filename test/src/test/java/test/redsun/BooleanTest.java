package test.redsun;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class BooleanTest {

    @Test
    public void testBoolean() {
        Boolean boolean1 = Boolean.TRUE;

        Boolean boolean2 = Boolean.TRUE;

        log.info("{}", boolean1.equals(boolean2));
        log.info("{}", boolean1 == boolean2);
    }

}
