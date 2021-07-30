package com.redsun.springlock.lock;

import com.redsun.springlock.annotate.Lock;
import com.redsun.springlock.util.SpringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.expression.AnnotatedElementKey;
import org.springframework.expression.EvaluationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * intercept
 *
 * @author qiquan
 * @date 2021/07/24 17:10
 */
@Aspect
@Component
public class LockInterceptor {

    @Autowired
    private ExpressionEvaluator<String> evaluator;


    @Around("@within(com.redsun.springlock.annotate.RedsunService)")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        if (signature instanceof MethodSignature) {
            Method targetMethod = evaluator.getTargetMethod(joinPoint.getTarget().getClass()
                    , ((MethodSignature)signature).getMethod());
            Lock lock = targetMethod.getAnnotation(Lock.class);
            if (lock == null) {
                return joinPoint.proceed();
            }
            String lockBean = lock.lockBean();
            String lockKey = lock.lockKey();
            if (!StringUtils.hasLength(lockBean) || !StringUtils.hasLength(lockKey)) {
                return joinPoint.proceed();
            }

            boolean lockResult = false;
            String lockKeyValue = null;
            LockI lockI = null;
            try {
                lockKeyValue = getValue(joinPoint, lock.lockKey(), targetMethod);
                lockI = SpringUtils.getBean(lockBean, LockI.class);
                lockResult = lockI.lock(lockKeyValue);
            } catch (Exception e) {
                // log

            } finally {
                if (lockResult) {
                    lockI.unlock(lockKeyValue);
                }
            }
        }
        return joinPoint.proceed();
    }

    /**
     *
     * @param joinPoint
     * @param condition
     * @return
     */
    private String getValue(ProceedingJoinPoint joinPoint, String condition, Method targetMethod) {
        Object[] args = joinPoint.getArgs();
        if (Objects.isNull(args)) {
            return null;
        }

        EvaluationContext evaluationContext = evaluator.createEvaluationContext(joinPoint.getTarget()
                , targetMethod, args);
        AnnotatedElementKey methodKey = new AnnotatedElementKey(targetMethod, joinPoint.getTarget().getClass());
        return evaluator.condition(condition, methodKey, evaluationContext, String.class);
    }
}
