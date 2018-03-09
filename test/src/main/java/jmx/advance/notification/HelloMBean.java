package jmx.advance.notification;

/**
 * 接口名必须以MBean结尾
 */
public interface HelloMBean {
    String getName();

    void setName(String name);

    void printHello();

    void printHello(String whoName);
}
