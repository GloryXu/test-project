package domain;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class Print {

    public static void printArray(List<String> a) {
        String str = "";
        for (String tem : a) {
            str += tem;
            str += ",";
        }
        log.info("------str = {}", str);
    }
}
