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

    /**
     * 前缀
     */
    public static final String LOCK_PREFIX = "AUTO_LOCK_PREFIX";


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

            if (!isValide(lock)) {
                // log
                return joinPoint.proceed();
            }

            String lockBean = lock.lockBean();
            String[] lockKeys = lock.lockKeys();

            boolean lockResult = false;
            String lockKeyValue = null;
            LockI lockI = null;
            try {
                lockKeyValue = getValue(joinPoint, lockKeys, targetMethod);
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
     * 是否合理
     *
     * @param lock
     * @return
     */
    private boolean isValide(Lock lock) {
        String lockBean = lock.lockBean();
        String[] lockKeys = lock.lockKeys();

        if (!StringUtils.hasLength(lockBean) || Objects.isNull(lockKeys) || lockKeys.length == 0) {
            return false;
        }

        // 任意lockKey不合法，则均不合法
        for (String lockKey : lockKeys) {
            if (!StringUtils.hasLength(lockKey)) {
                return false;
            }
        }

        int lockExpire = lock.lockExpire();
        if (lockExpire <= 0) {
            return false;
        }

        return true;
    }

    /**
     *
     * @param joinPoint
     * @param conditions
     * @return
     */
    private String getValue(ProceedingJoinPoint joinPoint, String[] conditions, Method targetMethod) {
        Object[] args = joinPoint.getArgs();
        if (Objects.isNull(args)) {
            return null;
        }

        EvaluationContext evaluationContext = evaluator.createEvaluationContext(joinPoint.getTarget()
                , targetMethod, args);
        AnnotatedElementKey methodKey = new AnnotatedElementKey(targetMethod, joinPoint.getTarget().getClass());

        StringBuilder finalValue = new StringBuilder(LOCK_PREFIX);
        for (String condition : conditions) {
            finalValue.append(evaluator.condition(condition, methodKey, evaluationContext, String.class));
        }

        return finalValue.toString();
    }
}
