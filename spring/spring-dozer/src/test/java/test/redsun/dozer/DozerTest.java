package test.redsun.dozer;

import com.redsun.dozer.A;
import com.redsun.dozer.B;
import com.redsun.dozer.C;
import com.redsun.dozer.DozerBeanMapper;
import org.junit.Test;
import test.redsun.base.BaseTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qiquan
 * @date 2020/06/19 14:05
 */
public class DozerTest extends BaseTest {

    @Test
    public void testA() {
        A a = new A();
        a.setA("aaaa");

        B b = new B();
        b.setB("bbb");
        List<String> listB = new ArrayList<>();
        listB.add("lista");
        b.setaList(listB);

        a.setB(b);

        List<String> listA = new ArrayList<>();
        listA.add("listb");
        a.setaList(listA);

        System.out.println("a=" + a);

        C c = DozerBeanMapper.map(context.getBean("mapper"), a, C.class);

        System.out.println("c=" + c);
    }

}
