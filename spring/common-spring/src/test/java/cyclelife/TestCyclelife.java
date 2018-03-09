package cyclelife;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestCyclelife {
    public static void main(String[] args){
        System.out.println("--------------【初始化容器】---------------");

        ApplicationContext context = new ClassPathXmlApplicationContext("spring/beans.xml");
        System.out.println("-------------------【容器初始化成功】------------------");
        //得到studentBean，并显示其信息
        StudentBean studentBean = context.getBean("studentBean",StudentBean.class);
        System.out.println(studentBean);

        System.out.println("--------------------【销毁容器】----------------------");
        ((ClassPathXmlApplicationContext)context).registerShutdownHook();
    }
}
