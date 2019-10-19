package classloader.multi;

/**
 * @author xuguangrong
 * @description 测试接口实现类
 * @date Created at 13:17 2019/9/22
 */
public class TestInterfaceImpl implements TestInterface {

    @Override
    public void test(int num) {
        System.out.println("Current ClassLoader is " + TestInterfaceImpl.class.getClassLoader()
                + ", num = " + num);
    }

}
