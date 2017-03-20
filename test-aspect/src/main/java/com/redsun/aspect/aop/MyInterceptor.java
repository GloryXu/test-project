package com.redsun.aspect.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * 切面
 * Created by xugr on 2017/3/20.
 */
@Slf4j
@Aspect
public class MyInterceptor {

    //定义一个切入点
    @Pointcut("execution(* com.redsun.aspect.impl.PersonServiceImpl.*(..))")
    private void anyMethod() { }

    @Before("anyMethod() && args(name)")
    public void doAccessCheck(String name) {
        log.info("args = {}", name);
        log.info("前置通知");
    }

    @AfterReturning("anyMethod()")
    public void doAfter() {
        log.info("后置通知");
    }

    @After("anyMethod()")
    public void after() {
        log.info("最终通知");
    }

    @AfterThrowing("anyMethod()")
    public void doAfterThrow() {
        log.info("例外通知");
    }

    @Around("anyMethod()")
    public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
        log.info("进入环绕通知");
        Object object = pjp.proceed();//执行该方法
        log.info("退出方法");
        return object;
    }
}
