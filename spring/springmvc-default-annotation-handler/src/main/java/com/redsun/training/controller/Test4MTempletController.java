package com.redsun.training.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/user")
public class Test4MTempletController {

    @RequestMapping(value={"/login/{user}/{pwd}"})
    public ModelAndView login(@PathVariable(value="user")String username,@PathVariable(value="pwd")String pwd){
        String msg = "";
        ModelAndView mv = new ModelAndView();
        System.out.println(username);
        if (!"test".equals(username)) {
            msg = "用户名不存在!";
        } else if (!"0000".equals(pwd)) {
            msg = "密码不正确!";
        } else {
            msg = "恭喜您登录成功!";
        }
        mv.addObject("msg", Test4MTempletController.class+"-"+msg);
        mv.setViewName("result");
        return mv;
    }

    @RequestMapping(value={"/login/{user}/mid/{pwd}"})
    public ModelAndView loginTest(@PathVariable(value="user")String username,@PathVariable(value="pwd")String pwd){
        String msg = "";
        ModelAndView mv = new ModelAndView();
        System.out.println(username);
        if(!username.equals("test")){
            msg="用户名不存在!";
        }else if(!pwd.equals("0000")){
            msg="密码不正确!";
        }else{
            msg="恭喜您登录成功!";
        }
        mv.addObject("msg", Test4MTempletController.class+"-"+msg);
        mv.setViewName("result");
        return mv;
    }

}
