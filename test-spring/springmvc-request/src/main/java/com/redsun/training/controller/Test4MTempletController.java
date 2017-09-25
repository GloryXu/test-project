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
        if(!username.equals("test")){
            msg="�û���������!";
        }else if(!pwd.equals("0000")){
            msg="���벻��ȷ!";
        }else{
            msg="��ϲ����¼�ɹ�!";
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
            msg="�û���������!";
        }else if(!pwd.equals("0000")){
            msg="���벻��ȷ!";
        }else{
            msg="��ϲ����¼�ɹ�!";
        }
        mv.addObject("msg", Test4MTempletController.class+"-"+msg);
        mv.setViewName("result");
        return mv;
	}
	
}
