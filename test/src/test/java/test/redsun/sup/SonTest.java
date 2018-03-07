package test.redsun.sup;

import sup.Parent;
import sup.Son;
import org.junit.Test;

public class SonTest {

    @Test
    public void testPrint() {
        Parent parent = new Parent();
        Parent son = new Son();

        parent.beat();
        son.beat();
    }
}
