package com.redsun.training.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class Test1SimpleUrlMappingController implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userName=request.getParameter("username");
        String pwd=request.getParameter("pwd");

        String msg="";
        ModelAndView mav=new ModelAndView("test1SimpleUrlMappingController");
        if(!userName.equals("test")){
            msg="用户名不存在!";
        }else if(!pwd.equals("0000")){
            msg="密码不正确!";
        }else{
            msg="恭喜您登录成功!";
        }

        mav.addObject("msg",msg);
        return mav;
    }

}
