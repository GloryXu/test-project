package base;

import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-multi-db-exchange.xml")
public abstract class BaseTest {

    protected static ApplicationContext context;

    /*static {
        System.out.println("--------------【初始化容器】---------------");
//        context = new ClassPathXmlApplicationContext("spring/beans.xml.bak");
        context = new ClassPathXmlApplicationContext("spring/spring-multi-db-exchange.xml");
        System.out.println("-------------------【容器初始化成功】------------------");
    }*/

}
