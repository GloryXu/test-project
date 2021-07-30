package com.redsun.springlock.controller;

import com.redsun.springlock.dto.Teacher;
import com.redsun.springlock.dto.User;

/**
 * @author qiquan
 * @date 2021/07/30 11:31
 */
public interface TestController {
    /**
     *
     * @param user
     * @return
     */
    String printInfo(User user);

    /**
     *
     * @param user
     * @param teacher
     * @return
     */
    String printInfo1(User user, Teacher teacher);

    /**
     *
     * @param user
     * @return
     */
    String printInfo2(User user);
}
