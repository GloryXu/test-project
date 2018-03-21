package invoke;

import org.junit.Test;

import static org.junit.Assert.*;

public class InvokeUtilsTest {

    private InvokeUtils invokeUtils = new InvokeUtils();

    @Test
    public void getProperty() throws Exception {
        User user = new User();
        user.setName("xugr");
        System.out.println(invokeUtils.getProperty(user, "name"));
        System.out.println(invokeUtils.getProperty(user, "name").getClass());
        System.out.println(invokeUtils.getProperty(user, "age"));
        System.out.println(invokeUtils.getProperty(user, "age").getClass());
    }

    @Test
    public void getStaticProperty() throws Exception {
        System.out.println(invokeUtils.getStaticProperty("invoke.User", "STATIC"));
    }

    @Test
    public void invokeMethod() throws Exception {
        User user = new User("xugr", 10);
        System.out.println(invokeUtils.invokeMethod(user, "info", new Object[]{}));
    }

    @Test
    public void invokeStaticMethod() throws Exception {
        System.out.println(invokeUtils.invokeStaticMethod("invoke.User", "staticMethod", new Object[]{}));
    }

    @Test
    public void newInstance() throws Exception {
        System.out.println(invokeUtils.newInstance("invoke.User",new Object[]{"xugr", 11}));
    }

}