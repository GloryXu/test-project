package collection;

import org.junit.Test;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author xuguangrong
 * @description set集合测试
 * @date Created at 13:03 2019/5/24
 */
public class SetTest {

    @Test
    public void testTreeSet () {
        Set<Double> set = new TreeSet<>();
        set.add(1.2);
        set.add(1.0);
        set.add(0.6);
        set.add(2.2);

        System.out.println(((TreeSet<Double>) set).pollFirst());
    }
}
