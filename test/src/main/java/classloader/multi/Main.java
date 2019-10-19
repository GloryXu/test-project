package classloader.multi;

import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @author xuguangrong
 * @description
 * @date Created at 13:20 2019/9/22
 */
public class Main {

    @Test
    public void test1() throws ClassNotFoundException {
        Class clazz = Class.forName("classloader.multi.TestInterface");

        TestInterface testInterface = new TestInterfaceImpl();
        System.out.println(clazz.isInstance(testInterface));
    }

    @Test
    public void test2() throws ClassNotFoundException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        Class clazz = Class.forName("classloader.multi.TestInterface");

        MyClassLoader loader = new MyClassLoader();
        Class<?> testInterface = loader.findClass("classloader.multi.TestInterfaceImpl");
        try {
            Object obj = testInterface.newInstance();

            System.out.println(clazz.isInstance(obj));
            System.out.println("clazz's classloader is " + clazz.getClassLoader());
            System.out.println("obj's classloader is " + obj.getClass().getClassLoader());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3() throws ClassNotFoundException {
        System.out.println(Thread.currentThread().getContextClassLoader());

        Class clazz = Class.forName("classloader.multi.TestInterface");

        MyClassLoader loader = new MyClassLoader();
        Class<?> testInterface = loader.findClass("classloader.multi.TestInterfaceImpl");
        try {
            Object obj = testInterface.newInstance();

            System.out.println(clazz.isInstance(obj));
            System.out.println(clazz.getClassLoader());
            System.out.println(obj.getClass().getClassLoader());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
