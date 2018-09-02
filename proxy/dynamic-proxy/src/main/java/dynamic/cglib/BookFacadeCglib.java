package dynamic.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class BookFacadeCglib implements MethodInterceptor {

    private Object target;// 业务类对象，供代理方法中进行真正的业务方法调用

    // 相当于JDK动态代理中的绑定
    public Object getInstance(Object target) {
        this.target = target;  // 给业务对象赋值
        Enhancer enhancer = new Enhancer(); // 创建加强器，用来创建动态代理类
        enhancer.setSuperclass(this.target.getClass());  // 为加强器指定要代理的业务类（即：为下面生成的代理类指定父类）
        // 设置回调：对于代理类上所有方法的调用，都会调用CallBack，而Callback则需要实现intercept()方法进行拦
        enhancer.setCallback(this);
        // 创建动态代理类对象并返回
        return enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("预处理——————");
        Object objReturn = methodProxy.invokeSuper(obj, args); // 调用业务类（父类中）的方法
        System.out.println("调用后操作——————");
        return null;
    }
}
