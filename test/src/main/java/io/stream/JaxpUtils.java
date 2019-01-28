package io.stream;

import java.io.*;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * @author xuguangrong
 * @description 操作XML文件的方法
 * @date Created at 14:18 2019/1/28
 */
public class JaxpUtils {
    static String path;

    static {
        path = new File("./test/src/main/java/io/stream/user.xml").getAbsolutePath();
    }

    public static Document getDocument() throws Exception{
        //创建一个dom4j解析器
        try {
            SAXReader reader = new SAXReader();
            Document document = reader.read(path);
            return document;
        } catch (DocumentException e) {
            throw e;
        }
    }

    public static void write2xml(Document document) throws FileNotFoundException, UnsupportedEncodingException {
        XMLWriter writer = new XMLWriter(new FileOutputStream(path), OutputFormat.createPrettyPrint());

//            writer.write(document) ;
//            writer.close() ;
    }
}
