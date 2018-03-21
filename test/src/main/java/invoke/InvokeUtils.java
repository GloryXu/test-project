package invoke;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class InvokeUtils {
    /**
     * 得到某个对象的属性
     *
     * @param owner
     * @param fieldName
     * @return
     * @throws Exception
     */
    public Object getProperty(Object owner, String fieldName) throws Exception {
        Class ownerClass = owner.getClass();
        Field field = ownerClass.getField(fieldName);
        Object property = field.get(owner);
        return property;
    }

    /**
     * 得到某个类的静态属性
     *
     * @param className
     * @param fieldName
     * @return
     * @throws Exception
     */
    public Object getStaticProperty(String className, String fieldName) throws Exception {
        Class ownerClass = Class.forName(className);
        Field field = ownerClass.getField(fieldName);
        Object property = field.get(ownerClass);
        return property;
    }

    /**
     * 执行某对象的方法
     *
     * @param owner
     * @param methodName
     * @param args
     * @return
     * @throws Exception
     */
    public Object invokeMethod(Object owner, String methodName, Object[] args) throws Exception {
        Class ownerClass = owner.getClass();
        Class[] argsClass = new Class[args.length];
        for (int i = 0, j = args.length; i < j; i++) {
            argsClass[i] = args[i].getClass();
        }
        Method method = ownerClass.getMethod(methodName, argsClass);
        return method.invoke(owner, args);
    }

    /**
     * 执行某个类的静态方法
     * @param className
     * @param methodName
     * @param args
     * @return
     * @throws Exception
     */
    public Object invokeStaticMethod(String className, String methodName, Object[] args) throws Exception {
        Class ownerClass = Class.forName(className);
        Class[] argsClass = new Class[args.length];
        for (int i = 0, j = args.length; i < j; i++) {
            argsClass[i] = args[i].getClass();
        }
        Method method = ownerClass.getMethod(methodName, argsClass);
        return method.invoke(null, args);
    }

    /**
     * 新建实例
     * @param className
     * @param args
     * @return
     * @throws Exception
     */
    public Object newInstance(String className, Object[] args) throws Exception {
        Class newoneClass = Class.forName(className);
        Class[] argsClass = new Class[args.length];
        for (int i = 0, j = args.length; i < j; i++) {
            argsClass[i] = args[i].getClass();
        }
        Constructor cons = newoneClass.getConstructor(argsClass);
        return cons.newInstance(args);
    }

}
