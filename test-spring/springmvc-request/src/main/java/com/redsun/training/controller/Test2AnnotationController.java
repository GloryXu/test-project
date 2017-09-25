package com.redsun.training.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.redsun.training.pojo.User;

@Controller
@RequestMapping(value="/user")
public class Test2AnnotationController {

	@RequestMapping(value="/login")
	public ModelAndView login(String username,String pwd){
		String msg = "";
		ModelAndView mv = new ModelAndView();
		System.out.println(username);
        if(!username.equals("test")){
            msg="�û���������!";
        }else if(!pwd.equals("0000")){
            msg="���벻��ȷ!";
        }else{
            msg="��ϲ����¼�ɹ�!";
        }
        mv.addObject("msg", "login-"+msg);
        mv.setViewName("result");
        return mv;
	}
	
	@RequestMapping(value="/login2Model")
	public ModelAndView login2Model(User user){
		String msg = "";
		String username = user.getName();
		String pwd = user.getPwd();
		System.out.println(user);
        if(!username.equals("test")){
            msg="�û���������!";
        }else if(!pwd.equals("0000")){
            msg="���벻��ȷ!";    
        }else{
            msg="��ϲ����¼�ɹ�!";
        }
        ModelAndView mv = new ModelAndView();
        mv.setViewName("result");
        mv.addObject("msg", "login2Model-"+msg);
        return mv;
	}
	
}
