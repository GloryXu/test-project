package base;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public abstract class BaseTest {

    protected static ApplicationContext context;

    static {
        System.out.println("--------------【初始化容器】---------------");
        context = new ClassPathXmlApplicationContext("spring/beans.xml");
        System.out.println("-------------------【容器初始化成功】------------------");
    }

}
