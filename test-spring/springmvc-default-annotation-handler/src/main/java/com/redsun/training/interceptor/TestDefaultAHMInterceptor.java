package com.redsun.training.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class TestDefaultAHMInterceptor implements HandlerInterceptor {

	//Action֮ǰִ��
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(TestDefaultAHMInterceptor.class+"--------------preHandle");
		return true;
	}

	//������ͼ֮ǰִ��
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(TestDefaultAHMInterceptor.class+"-----------------postHandle");
	}

	//��Ⱦ����ͼ��ִ�еķ���
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println(TestDefaultAHMInterceptor.class+"-----------------afterCompletion");
	}

}
