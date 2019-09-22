package classloader;

import classloader.entity.Child;

/**
 * static变量发生在静态解析阶段，也即是初始化之前，此时已经将字段的符号引用转化为了内存引用，也便将它与对应的类关联在了一起，
 * 由于在子类中没有查找到与 m 相匹配的字段，那么 m 便不会与子类关联在一起，因此并不会触发子类的初始化。
 * <p>
 *
 * 至于为什么先执行super的类静态语句块，后执行父类静态语句块是因为在初始化阶段子类的<clinit>调用前保证父类的<clinit>被调用
 */
public class StaticTest {
    public static void main(String[] args) {
        System.out.println(Child.m);
    }
}
