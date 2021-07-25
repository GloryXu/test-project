package com.redsun.springlock.lock;

/**
 * @author qiquan
 * @date 2021/07/24 19:05
 */

public class ExpressionRootObject {

    private final Object object;

    private final Object[] args;

    public ExpressionRootObject(Object object, Object[] args) {
        this.object = object;
        this.args = args;
    }

    public Object getObject() {
        return object;
    }

    public Object[] getArgs() {
        return args;
    }
}