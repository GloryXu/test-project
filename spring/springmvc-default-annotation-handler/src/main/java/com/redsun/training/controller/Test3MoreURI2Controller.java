package com.redsun.training.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/user1")
public class Test3MoreURI2Controller {

    @RequestMapping(value={"/login","/login2Model","/hehehe"})
    public ModelAndView login(String username,String pwd){
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
        mv.addObject("msg", "login-"+msg);
        mv.setViewName("result");
        return mv;
    }

}
