package com.redsun.springlock.controller;

import com.redsun.springlock.annotate.Lock;
import com.redsun.springlock.annotate.RedsunService;
import com.redsun.springlock.dto.User;
import org.springframework.web.bind.annotation.RestController;

/**
 * test controller
 *
 * @author qiquan
 * @date 2021/07/24 16:41
 */
@RestController
@RedsunService
public class TestController {

    @Lock(lockBean = "lockBean", lockExpire = 10, lockKey = "#user.name")
    public String printInfo(User user) {
        String info = "hahha";
        System.out.println(info);

        return info;
    }

}
