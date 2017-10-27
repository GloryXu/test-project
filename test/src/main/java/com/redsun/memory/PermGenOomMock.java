package com.redsun.memory;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * 永久代内存溢出
 * 1.7存在，1.8 metaspace取代
 */
public class PermGenOomMock {
    public static void main(String[] args) {
        URL url;
        List<ClassLoader> classLoaderList = new ArrayList<ClassLoader>();
        try {
            url = new File("/tmp").toURI().toURL();
            URL[] urls = {url};
            while (true) {
                ClassLoader loader = new URLClassLoader(urls);
                classLoaderList.add(loader);
                loader.loadClass("com.redsun.memory.Test");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
