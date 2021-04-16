package string;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import domain.TestPDU;
import lombok.extern.slf4j.Slf4j;
import net.sf.oval.Validator;
import org.junit.Test;
import socket.SocketUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by DELL on 2017/7/4.
 */
@Slf4j
public class JsonTest {

    @Test
    public void testConvert() {
        String str = "{\"test1\":\"\",\"test2\":\"value2\"}";

        Gson gson = new Gson();
        TestPDU testPDU = gson.fromJson(str, TestPDU.class);

        log.info("validete = {}", new Validator().validate(testPDU));
        log.info("result = {}", testPDU);
    }

    @Test
    public void testJSONConvert() {
        Map<String, String> mockData = new HashMap<>();
        mockData.put("1", "哈哈哈");
        mockData.put("2", "3333");

        System.out.println(JSON.parseObject(JSON.toJSONString(mockData), Map.class));
        System.out.println(JSON.parseObject(JSON.toJSONString(mockData), Map.class).getClass().getSimpleName());
    }
}
