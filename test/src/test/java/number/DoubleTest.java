package number;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;
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
        Double d = 0.00000004d;

        Map<String, Object> map = new HashMap<>();

        map.put("double", d);

        System.out.println(map.toString());

        System.out.println(JSONObject.toJSON(map));

        DecimalFormat df = new DecimalFormat("0.00000000");
        System.out.println(df.format(((JSONObject) JSONObject.toJSON(map)).getDouble("double")));

        System.out.println(BigDecimal.valueOf((Double) map.get("double")).toPlainString());
        System.out.println(BigDecimal.valueOf((Double) map.get("double")).toString());
        System.out.println(JSON.toJSONString(map));
    }
}
