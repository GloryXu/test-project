package com.redsun.springlock.annotate;

import org.springframework.beans.factory.annotation.Required;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * lock
 *
 * @author qiquan
 * @date 2021/07/24 16:35
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Lock {
    /**
     * bean name
     *
     * @return
     */
    String lockBean();

    /**
     * key
     *
     * @return
     */
    String[] lockKeys();

    /**
     * lock expire time
     *
     * @return
     */
    int lockExpire() default 10;

}
