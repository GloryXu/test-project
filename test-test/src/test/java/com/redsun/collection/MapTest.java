package com.redsun.collection;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Slf4j
public class MapTest {

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

    @Test
    public void testMapKeyValueIsNull () {
        // Hashtable(线程安全) ConcurrentHashMap(分段锁技术) TreeMap HashMap(key can be null)
        Set<String> sets = new HashSet<>();
        sets.add("xugr");
        sets.add("xugr");

        log.info("sets = {}", sets);
    }
}
