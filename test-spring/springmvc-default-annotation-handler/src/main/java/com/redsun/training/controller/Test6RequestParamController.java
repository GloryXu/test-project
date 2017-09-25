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
        if(!username.equals("test")){
            msg="�û���������!";
        }else if(!pwd.equals("0000")){
            msg="���벻��ȷ!";
        }else{
            msg="��ϲ����¼�ɹ�!";
        }
        mv.addObject("msg", "loginGet channelId="+username+";connectId="+pwd);
        mv.setViewName("result");
        return mv;
	}
	
	//method = {RequestMethod.POST, RequestMethod.GET}
	//@RequestMapping(params="!create", method=RequestMethod.GET)��
	//@RequestMapping(params="create", method=RequestMethod.GET) ����ʾ�������С�create���Ĳ�����,�����󷽷�Ϊ��GET������ƥ��
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
