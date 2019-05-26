package collection;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;

@Slf4j
public class MapTest {
    /**
     * 打印map的keySet和entrySet
     */
    @Test
    public void testMap () {
        Map<String, String> map = new HashMap<>();
        map.put("xugrkey1", "xugrvalue1");
        map.put("xugrkey2", "xugrvalue2");
        map.put("xugrkey3", "xugrvalue3");

        Set<String> keySet = map.keySet();
        log.info("--------keySet = {}", keySet);
        Set<Map.Entry<String, String>> entries = map.entrySet();
        log.info("--------entries = {}", entries);
    }

    /**
     * hashSet的key是否可以为null
     */
    @Test
    public void testMapKeyValueIsNull () {
        // Hashtable(线程安全) ConcurrentHashMap(分段锁技术) TreeMap HashMap(key can be null)
        Set<String> sets = new HashSet<>();// 底层为hashMap存储
        sets.add("xugr");
        sets.add("xugr");
        sets.add(null);

        log.info("sets = {}", sets);
    }

    /**
     * entrySet打印key和value
     */
    @Test
    public void testMapEntrySet() {
        Map<String,String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        Set<Map.Entry<String, String>> sets = map.entrySet();
        for(Map.Entry<String, String> set : sets) {
            log.info("key = " + set.getKey() + ", value = " + set.getValue());
        }
    }

    /**
     * map put操作返回的值，如果之前已经存在该key，则返回原来的值，否则返回null
     */
    @Test
    public void testMapPutReturnValue() {
        Map<String, String> hashTable = new Hashtable<>();
        Map<String, String> hashMap = new HashMap<>();

        hashMap.put("xugr", "old");
        log.info(hashMap.put("xugr", "new"));
        log.info(hashMap.put("newkey", "newkey"));

        hashTable.put("xugr1", "old");
        log.info(hashTable.put("xugr1", "new"));
        log.info(hashTable.put("newkey", "newkey"));
    }

    /**
     * 测试强制转换，当为null时是否可以强制转换
     */
    @Test
    public void testForceTransform() {
        Map<String, Object> testMap = new HashMap<>();
        Map<String, String> subMap = (Map<String, String>) testMap.get("xugr");
        log.info("{}", subMap);
    }

    @Test
    public void testMapRemoveKeyValue() {
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        System.out.println(map);

        Iterator<Map.Entry<String, String>> iterable = map.entrySet().iterator();
        while(iterable.hasNext()) {
            Map.Entry<String, String> item = iterable.next();
            if (item.getKey().equals("key1")) {
                iterable.remove();
            }
        }

        System.out.println(map);
    }

    @Test
    public void compareMap() {
        Map<Integer, String> cutFlowMap = new TreeMap<>(Integer::compareTo);
        cutFlowMap.put(10, "xugr10");
        cutFlowMap.put(1, "xugr1");
        cutFlowMap.put(20, "xugr20");
        cutFlowMap.put(100, "xugr100");
        cutFlowMap.put(-3, "xugr-3");

        System.out.println(cutFlowMap);

        System.out.println(cutFlowMap.keySet());
    }

}
