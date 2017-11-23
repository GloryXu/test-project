package test.redsun.properties;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Properties;

@Slf4j
public class PropertiesTest {

    @Test
    public void testGetSet() {
        Properties properties = new Properties();
        properties.setProperty("xugr", "xugrvalue1");
        log.info("first value : {}", properties.getProperty("xugr"));
        properties.setProperty("xugr", "xugrvalue2");
        log.info("get value : {}", properties.get("xugr"));
        log.info("second value : {}", properties.getProperty("xugr"));
    }
}
