package number;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xuguangrong
 * @description Duble测试
 * @date Created at 11:40 2019/6/27
 */
public class DoubleTest {

    @Test
    public void testConvertJSON() {
        Double d = 0.000004d;

        Map<String, Object> map = new HashMap<>();

        map.put("double", d);

        System.out.println(map.toString());

        System.out.println(JSONObject.toJSON(map));
    }
}
