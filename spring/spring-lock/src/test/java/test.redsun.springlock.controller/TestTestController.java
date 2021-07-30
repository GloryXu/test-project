package test.redsun.springlock.controller;

import com.redsun.springlock.controller.TestController;
import com.redsun.springlock.dto.Teacher;
import com.redsun.springlock.dto.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * test
 *
 * @author qiquan
 * @date 2021/07/24 16:55
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class TestTestController {

    @Autowired
    private TestController testController;

    @Test
    public void testPrintInfo() {
        User user = new User();
        user.setAge(10);
        user.setName("user");

        testController.printInfo(user);
    }

    @Test
    public void testPrintInfo1() {
        User user = new User();
        user.setAge(10);
        user.setName("user");
        user.setNation("china");

        Teacher teacher = new Teacher();
        teacher.setAge(20);
        teacher.setName("teacher");

        testController.printInfo1(user, teacher);
    }

    @Test
    public void testPrintInfo2() {
        User user = new User();
        user.setAge(10);
        user.setName("user");

        Teacher teacher = new Teacher();
        teacher.setName("teacher");
        teacher.setAge(20);
        user.setTeacher(teacher);

        testController.printInfo2(user);
    }

}
