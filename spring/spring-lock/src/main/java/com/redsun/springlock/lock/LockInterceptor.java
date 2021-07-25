package com.redsun.springlock.lock;

import com.redsun.springlock.annotate.Lock;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.expression.AnnotatedElementKey;
import org.springframework.expression.EvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * intercept
 *
 * @author qiquan
 * @date 2021/07/24 17:10
 */
@Aspect
@Component
public class LockInterceptor {

    private ExpressionEvaluator<String> evaluator = new ExpressionEvaluator<>();


    @Around("@within(com.redsun.springlock.annotate.RedsunService)")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature)signature;
        Method targetMethod = methodSignature.getMethod();
        Lock lock = targetMethod.getAnnotation(Lock.class);
        if (lock != null) {
            String result = getValue(joinPoint, lock.lockKey());
            System.out.println("running entity check: " + joinPoint.getSignature().getName());
        }
        return joinPoint.proceed();
    }

    /**
     *
     * @param joinPoint
     * @param lockKey
     * @return
     */
    private String getValue(ProceedingJoinPoint joinPoint, String lockKey) {
        return getValue(joinPoint.getTarget()
                , joinPoint.getArgs()
                , joinPoint.getTarget().getClass()
                , ((MethodSignature) joinPoint.getSignature()).getMethod()
                , lockKey);
    }

    /**
     *
     * @param object
     * @param args
     * @param clazz
     * @param method
     * @param condition
     * @return
     */
    private String getValue(Object object, Object[] args, Class clazz, Method method, String condition) {
        if (args == null) {
            return null;
        }
        EvaluationContext evaluationContext =
                evaluator.createEvaluationContext(object, clazz, method, args);
        AnnotatedElementKey methodKey = new AnnotatedElementKey(method, clazz);
        return evaluator.condition(condition, methodKey, evaluationContext, String.class);
    }

}
