package com.redsun.springlock.controller;

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
}
