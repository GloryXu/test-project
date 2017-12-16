package com.redsun.training.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/userTestRequestParam")
public class Test6RequestParamController {

    //@RequestParam(value="username", required=true, defaultValue="XXX")
    @RequestMapping(value={"/login"},method = RequestMethod.GET)
    public ModelAndView loginGet(@RequestParam(value="name",required=false)String username,String pwd){
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
        mv.addObject("msg", "loginGet channelId="+username+";connectId="+pwd);
        mv.setViewName("result");
        return mv;
    }

    //method = {RequestMethod.POST, RequestMethod.GET}
    //@RequestMapping(params="!create", method=RequestMethod.GET)：
    //@RequestMapping(params="create", method=RequestMethod.GET) ：表示请求中有“create”的参数名,且请求方法为“GET”即可匹配
    //@RequestMapping(params="submitFlag=create", method=RequestMethod.GET)
    //@RequestMapping(params="submitFlag!=create", method=RequestMethod.GET)
    @RequestMapping(value={"/login/{user:\\d{4}}/{pwd:\\d{6}}"},method = RequestMethod.POST)
    public ModelAndView loginPost(@PathVariable(value="user")String username,@PathVariable(value="pwd")String pwd){
        String msg = "";
        ModelAndView mv = new ModelAndView();
        System.out.println(username);
        mv.addObject("msg", "loginPost channelId="+username+";connectId="+pwd);
        mv.setViewName("result");
        return mv;
    }

}
