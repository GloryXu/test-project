package com.redsun.training.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.redsun.training.pojo.User;

public class HelloWorldController implements Controller {

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        //1、收集参数、验证参数
        //2、绑定参数到命令对象
        //3、将命令对象传入业务对象进行业务处理
        //4、选择下一个页面
        String userName = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        ModelAndView mv = new ModelAndView();
        //添加模型数据可以是任意的POJO对象
        User user = new User(userName, age);
        mv.addObject("message", "Hello!" + userName);
        mv.addObject("user", user);
        //设置逻辑视图名，视图解析器会根据该名字解析到具体的视图页面
        mv.setViewName("hello");
        return mv;
    }
}
