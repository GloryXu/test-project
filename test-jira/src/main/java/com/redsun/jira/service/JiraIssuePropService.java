package com.redsun.jira.service;

/**
 * 问题属性服务
 *
 * Created by xugr on 2017/4/7.
 */
public interface JiraIssuePropService {

    Object getPropertiesKeys();

    Boolean deleteProperty();

    Boolean setProperty();

    Object getProperty();
}
