package collection;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static domain.Print.printArray;

@Slf4j
public class ListTest {

    @Test
    public void testSubList() {
        List<String> list = new ArrayList<>();
        list.add("xugr1");
        list.add("xugr2");
        list.add("xugr3");

        List<String> subList = list.subList(2,3);
        log.info("----------------subList = {}", list);

        log.info("----------------subList remove = {}", subList.get(0));
        subList.remove(0);

        log.info("----------------subList = {}", list);
    }

    @Test
    public void testToArray () {
        List<String> list = new ArrayList<>(2);
        list.add("guan");
        list.add("bao");
        String[] array = new String[list.size()];
        array = list.toArray(array);
//        list.toArray()

        log.info("-------array[0] = {}, array[1] = {}", array[0], array[1]);
    }

    @Test
    public void testAsList() {
        String[] str = new String[] { "a", "b" };
        List list = Arrays.asList(str);

        log.info("-----old list[0] = {}", list.get(0));
        str[0] = "c";
        log.info("-----new list[0] = {}", list.get(0));

//        list.add("c");
    }

//    不要在 foreach 循环里进行元素的 remove/add 操作。remove 元素请使用 Iterator
//    方式，如果并发操作，需要对 Iterator 对象加锁

    @Test
    public void testForeachRemove() {
        List<String> a = new ArrayList<>();
        a.add("1");
        a.add("2");
        for (String temp : a) {
            if ("1".equals(temp)) {
                a.remove(temp);
            }
        }

        // throw exception : java.util.ConcurrentModificationException
        /*for (String temp : a) {
            if("1".equals(temp)){
                a.remove(temp);
            }
        }*/
        printArray(a);
    }
}
