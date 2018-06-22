package cyclelife;

import base.BaseTest;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestCyclelife extends BaseTest {

    @Test
    public void testCycleLife(){
        //得到studentBean，并显示其信息
        StudentBean studentBean = context.getBean("studentBean",StudentBean.class);
        System.out.println(studentBean);
        System.out.println("--------------------【销毁容器】----------------------");
        ((ClassPathXmlApplicationContext)context).registerShutdownHook();
    }
}
