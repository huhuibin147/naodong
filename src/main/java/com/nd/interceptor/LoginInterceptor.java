package com.nd.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 *  登录认证的拦截器 
 */
public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception exc)
			throws Exception {
		System.out.println("afterCompletion");
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView modelAndView) throws Exception {
		System.out.println("postHandle");		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		String uri = request.getRequestURI();
		if(uri.contains("login")||uri.contains("sendIdentifyCode")){
			System.out.println("uri通过...进入拦截器链...");
			return true;
		}
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		System.out.println("用户名为:"+username);
		if(username!=null){
			System.out.println("用户名不为空且为:"+username);
			return true;
		}
		System.out.println("用户未登陆，准备跳转");
//		response.sendRedirect(request.getContextPath() + "/html/login.html"); 	
		return false;
	}
	
}
