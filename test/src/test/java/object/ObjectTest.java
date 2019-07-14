package object;

import org.junit.Test;

/**
 * @author xuguangrong
 * @description 对象类测试
 * @date Created at 11:31 2019/6/3
 */
public class ObjectTest {

    @Test
    public void testGetClass() {
        Integer a = new Integer(3);

        Object aobj = a;

        System.out.println(aobj.getClass().getName());
        
        boolean flag = false;
        System.out.println(flag);
    }
}
