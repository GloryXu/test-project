package com.redsun.springlock.lock;

import org.springframework.aop.support.AopUtils;
import org.springframework.context.expression.AnnotatedElementKey;
import org.springframework.context.expression.CachedExpressionEvaluator;
import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author qiquan
 * @date 2021/07/24 19:03
 */
@Service
public class ExpressionEvaluator<T> extends CachedExpressionEvaluator {
    private final ParameterNameDiscoverer paramNameDiscoverer = new DefaultParameterNameDiscoverer();

    private final Map<ExpressionKey, Expression> conditionCache = new ConcurrentHashMap<>(64);

    private final Map<AnnotatedElementKey, Method> targetMethodCache = new ConcurrentHashMap<>(64);

    /**
     * Create the suitable {@link EvaluationContext} for the specified event handling on the
     * specified method.
     */
    public EvaluationContext createEvaluationContext(Object object, Method targetMethod, Object[] args) {
        ExpressionRootObject root = new ExpressionRootObject(object, args);
        return new MethodBasedEvaluationContext(root, targetMethod, args, this.paramNameDiscoverer);
    }

    /**
     * Specify if the condition defined by the specified expression matches.
     */
    public T condition(String conditionExpression, AnnotatedElementKey elementKey
            , EvaluationContext evalContext, Class<T> clazz) {
        return getExpression(this.conditionCache, elementKey, conditionExpression).getValue(evalContext, clazz);
    }

    /**
     *
     * @param targetClass
     * @param method
     * @return
     */
    public Method getTargetMethod(Class<?> targetClass, Method method) {
        AnnotatedElementKey methodKey = new AnnotatedElementKey(method, targetClass);
        Method targetMethod = this.targetMethodCache.get(methodKey);
        if (targetMethod == null) {
            targetMethod = AopUtils.getMostSpecificMethod(method, targetClass);
            if (targetMethod == null) {
                targetMethod = method;
            }
            this.targetMethodCache.put(methodKey, targetMethod);
        }
        return targetMethod;
    }

}
