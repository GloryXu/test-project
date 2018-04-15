import invoke.User;
import org.junit.Test;
import util.PrintUtils;

import java.util.*;

import static util.PrintUtils.parintArr;

public class NormalTest {
    @Test
    public void test1() {
        String a = null;
        String b = "xugr";
        System.out.println(Objects.equals(a, b));
    }
    @Test
    public void test2() {
        String str = "a,b,c,,";
        String[] arr = str.split(",",9);
        System.out.println(arr.length);
        System.out.println(parintArr(arr));
    }

    @Test
    public void testClone() throws CloneNotSupportedException {
        User user = new User();
        user.setName("hah");
        user.setAge(11);
        user.setDate(new Date());
        User userClone = (User) user.clone();
//        System.out.println(user == userClone);

//        System.out.println(user);
//        System.out.println(userClone);
        System.out.println(user.getDate() == userClone.getDate());

        user.setAge(1233);
//        System.out.println(user);
//        System.out.println(userClone);
        System.out.println(user.getDate() == userClone.getDate());
    }

    @Test
    public void testArrayList() {
        List<String> arratList = new ArrayList<>(5);
        arratList.add("1");
        arratList.add("2");
        arratList.add("3");
        arratList.add("4");
        arratList.add("5");
        List subList = arratList.subList(2,4);
//        System.out.println(subList.get(0));
//        System.out.println(subList.get(1));
//        subList.set(0, "ddd");
//        System.out.println(PrintUtils.parintArrayList(subList));
//        System.out.println(PrintUtils.parintArrayList(arratList));

        new Thread(() -> {
            arratList.remove(3);
        }).start();
        /**
         * 可能抛出异常
         * ConcurrentModificationException
         */
        for(Iterator iterable = subList.iterator(); iterable.hasNext();) {
            System.out.println();
            System.out.println(iterable.next());
        }

        System.out.println(PrintUtils.parintArrayList(arratList));
        System.out.println(PrintUtils.parintArrayList(subList));
    }

    @Test
    public void testToArray() {
        List<String> list = new ArrayList<>(2);
        list.add("guan");
        list.add("bao");

        String[] array = new String[list.size()];
        list.toArray(array);
        System.out.println(PrintUtils.parintArr(array));

        /**
         * 会报异常
         * java.lang.ClassCastException: [Ljava.lang.Object; cannot be cast to [Ljava.lang.String;
         */
//        String[] array1 = new String[list.size()];
//        array1 = (String[]) list.toArray();
//        System.out.println(PrintUtils.parintArr(array1));
    }

    @Test
    public void testRemove() {
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        for (String item : list) {
            // 改成2时异常ConcurrentModificationException
            if ("2".equals(item)) {
                list.remove(item);
            }
        }

        System.out.println(PrintUtils.parintArrayList(list));
    }

}
