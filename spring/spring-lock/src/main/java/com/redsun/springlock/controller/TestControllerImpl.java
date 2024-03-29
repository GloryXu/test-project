package com.redsun.springlock.controller;

import com.redsun.springlock.annotate.Lock;
import com.redsun.springlock.annotate.RedsunService;
import com.redsun.springlock.dto.Teacher;
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
public class TestControllerImpl implements TestController {

    @Override
    @Lock(lockBean = "lockBean", lockExpire = 10, lockKeys = {"#user.name", "#user.age"})
    public String printInfo(User user) {
        String info = "hahha";
//        System.out.println(info);

        return info;
    }

    @Override
    @Lock(lockBean = "lockBean", lockKeys = {"#user.name", "#user.nation", "#teacher.name", "#user.age", "#teacher.age"})
    public String printInfo1(User user, Teacher teacher) {
        String info = "hahha";
//        System.out.println(info);

        return info;
    }

    @Override
    @Lock(lockBean = "lockBean", lockKeys = {"#user.name", "#user.teacher.name", "#user.teacher.age"})
    public String printInfo2(User user) {

        return null;
    }

}
