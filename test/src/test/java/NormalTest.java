import invoke.User;
import org.junit.Test;
import util.PrintUtils;

import java.util.*;

import static util.PrintUtils.printArr;

public class NormalTest {

    @Test
    public void testForEach() {
        List<String> test = new ArrayList<>();
        test.add("1");
        test.add("2");
        test.add("3");

        test.forEach(t ->{
            if ("2".equals(t)) {
                return;
            }
            System.out.println(t);
        });
    }

    @Test
    public void testInstanceof() {
        String params = "ddd";
        System.out.println(params instanceof String);
    }

    @Test
    public void test1() {
        String a = null;
        String b = "xugr";
        System.out.println(Objects.equals(a, b));
    }

    @Test
    public void test2() {
        String str = "a,b,c,,";
        String[] arr = str.split(",", 9);
        System.out.println(arr.length);
        System.out.println(printArr(arr));
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
        List subList = arratList.subList(2, 4);
//        System.out.println(subList.get(0));
//        System.out.println(subList.get(1));
//        subList.set(0, "ddd");
//        System.out.println(PrintUtils.printArrayList(subList));
//        System.out.println(PrintUtils.printArrayList(arratList));

        new Thread(() -> {
            arratList.remove(3);
        }).start();
        /**
         * 可能抛出异常
         * ConcurrentModificationException
         */
        for (Iterator iterable = subList.iterator(); iterable.hasNext(); ) {
            System.out.println();
            System.out.println(iterable.next());
        }

        System.out.println(PrintUtils.printArrayList(arratList));
        System.out.println(PrintUtils.printArrayList(subList));
    }

    @Test
    public void testToArray() {
        List<String> list = new ArrayList<>(2);
        list.add("guan");
        list.add("bao");

        String[] array = new String[list.size()];
        list.toArray(array);
        System.out.println(PrintUtils.printArr(array));

        /**
         * 会报异常
         * java.lang.ClassCastException: [Ljava.lang.Object; cannot be cast to [Ljava.lang.String;
         */
//        String[] array1 = new String[list.size()];
//        array1 = (String[]) list.toArray();
//        System.out.println(PrintUtils.printArr(array1));
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
        System.out.println(PrintUtils.printArrayList(list));

    }

    /**
     * 从 13  13.2  13.4  13.6 ... 21   中随机四个数字取平均，多次得出大概率数字
     */
    @Test
    public void testRandom() {
        List<Double> range = genRange();

        Map<String, Integer> finalResult = null;
        Map<String, Integer> finalResultHashMap = null;

        int[] times = new int[]{100, 1000, 10000, 100000, 1000000};
        for (int i = 0; i < times.length; i++) {
            finalResultHashMap = new HashMap<>();
            finalResult = new TreeMap(new ValueComparator(finalResultHashMap));
            for (int j = 0; j < times[i]; j++) {
                // 取出四个数字
                double count = 0;
                for (int index = 0; index < 4; index++) {
                    count += range.get(new Random().nextInt(range.size()));
                }

                // 将结果留存下来
                operateResult(finalResultHashMap, count / 4);
            }
            finalResult.putAll(finalResultHashMap);
            System.out.println(times[i] + "次结果如下：" + finalResult);
        }
    }

    private void operateResult(Map<String, Integer> finalResult, double v) {
        double start = 13.0;

        while (!(start <= v && v < start + 0.2)) {
            start += 0.2;
        }
        String key = start + "~" + (start + 0.2);
        finalResult.put(key, (finalResult.get(key) == null ? 0 : finalResult.get(key)) + 1);
    }

    private List<Double> genRange() {
        List<Double> result = new ArrayList<>();
        Double start = new Double(13);
        while (start <= 21) {
            result.add(start);
            start = start + 0.2;
        }
//        System.out.println(PrintUtils.printArrayList(result));
        return result;
    }


    class ValueComparator implements Comparator<String> {

        Map<String, Integer> base;

        public ValueComparator(Map<String, Integer> base) {
            this.base = base;
        }

        // Note: this comparator imposes orderings that are inconsistent with equals.
        public int compare(String a, String b) {
            if (base.get(a) >= base.get(b)) {
                return -1;
            } else {
                return 1;
            } // returning 0 would merge keys
        }

    }

    @Test
    public void printEnum() {
        System.out.println(Type.DESC.name());
    }

    enum Type {
        DESC,
        ASC
    }

}