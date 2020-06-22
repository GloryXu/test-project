/**
 * Project: wmpgateway-common
 * 
 * File Created at 2014-7-9
 * 
 */
package com.redsun.dozer;

import org.dozer.Mapper;
import org.dozer.spring.DozerBeanMapperFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 */
public class DozerBeanMapper implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        DozerBeanMapper.applicationContext = applicationContext;
    }

    /**
     * 基于Dozer转换对象的类型.
     */
    public static <T> T map(Object mapper, Object source, Class<T> destinationClass) {
        return ((Mapper)mapper).map(source, destinationClass);
    }

    /**
     * 基于Dozer转换Collection中对象的类型.
     * 
     * @param <R>
     */
    public static <R, T> List<T> mapList(Object mapper, Collection<R> sourceList, Class<T> destinationClass) {
        List<T> destinationList = new ArrayList<>();
        for (R sourceObject : sourceList) {
            T destinationObject = ((Mapper)mapper).map(sourceObject, destinationClass);
            destinationList.add(destinationObject);
        }
        return destinationList;
    }

    /**
     * 基于Dozer将对象A的值拷贝到对象B中.
     */
    public static void copy(Object mapper, Object source, Object destinationObject) {
        ((Mapper)mapper).map(source, destinationObject);
    }
}
