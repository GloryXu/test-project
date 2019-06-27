package json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jdk.nashorn.internal.parser.JSONParser;

import java.util.Map;

/**
 * @author xuguangrong
 * @description json性能比较
 * @date Created at 13:54 2019/6/2
 */
public class JSONMain {

    /**
     * json解析性能比较
     *
     * 与gson、jackson
     * @param args
     */
    public static void main(String[] args) {
        String jsonStr = "{'key':null,'xugr':'haha','keysss':'哈哈哈'}";
        System.out.println(JSON.toJSONString(jsonStr,SerializerFeature.WriteNullListAsEmpty));


        String json = "{'key':'xugr','xugr':'haha','keysss':'哈哈哈'}";

        int times = 1;
        long start = System.currentTimeMillis();
        for (int i = 0;i<times;i++) {
            JsonParser jsonParser = new JsonParser();
            jsonParser.parse(json);
        }
        System.out.println("gson 耗时：" + (System.currentTimeMillis() - start));

        long start1 = System.currentTimeMillis();
        for (int i = 0;i<times;i++) {
            JSON.parseObject(json);
        }
        System.out.println("fastjson 耗时：" + (System.currentTimeMillis() - start1));
    }

}
