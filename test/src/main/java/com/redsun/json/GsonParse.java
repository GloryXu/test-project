package com.redsun.json;

import com.google.gson.Gson;
import org.springframework.util.StringUtils;

import java.util.Map;

public class GsonParse {
    /**
     * 从JsonStr中解析User
     * @param jsonStr
     * @return
     */
    public static User decodeFromJson(String jsonStr) {
        User user;
        if(StringUtils.isEmpty(jsonStr)){
            return new User();
        }
        Gson gson = new Gson();
        user = gson.fromJson(jsonStr, User.class);
        return user;
    }

    /**
     * 从JsonStr中解析Map
     * @param jsonStr
     * @return
     */
    public static Map<String, Object> decodeFromJsonToMap(String jsonStr) {
        Map<String, Object> user;
        Gson gson = new Gson();
        user = gson.fromJson(jsonStr, Map.class);
        return user;
    }

    /**
     * 将user转换为JsonStr
     * @param user
     * @return
     */
    public static String encodeToJson(User user){
        Gson gson = new Gson();
        String jsonStr = gson.toJson(user);
        return jsonStr;
    }

    public static void main(String[] args) {
//        String jsonStr = "{\"username\":\"Glory\",\"age\":\"10\",\"password\":\"pwd\"}";
        String jsonStr = "{\"username\":\"Glory\",\"age\":10,\"password\":\"pwd\"}";
        User user = decodeFromJson(jsonStr);
        System.out.println(decodeFromJson(jsonStr));
        System.out.println(decodeFromJsonToMap(jsonStr));
        System.out.println(encodeToJson(user));
    }
}
