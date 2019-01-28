package io.stream;

import org.dom4j.Document;

/**
 * @author xuguangrong
 * @description 模拟文件提前结束异常
 * @date Created at 14:18 2019/1/28
 */
public class FileAdvanceOver {

    public static void main(String[] args) {
        try {
            Document document = JaxpUtils.getDocument();
            JaxpUtils.write2xml(document);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
