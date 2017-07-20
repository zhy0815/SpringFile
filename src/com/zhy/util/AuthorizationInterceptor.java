package com.zhy.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.zhy.model.Admin;

public class AuthorizationInterceptor implements HandlerInterceptor {
	// 不拦截"/loginForm"和"/login"请求
	private static final String[] IGNORE_URI={"/loginForm","/login"};
	 /** 
     * 该方法将在整个请求完成之后执行， 主要作用是用于清理资源的，
     * 该方法也只能在当前Interceptor的preHandle方法的返回值为true时才会执行。 
     */  
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		System.out.println("afterCompletion--->");
	}
	/** 
     * 该方法将在Controller的方法调用之后执行， 方法中可以对ModelAndView进行操作 ，
     * 该方法也只能在当前Interceptor的preHandle方法的返回值为true时才会执行。 
     */
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		System.out.println("postHandle-->");
	}
	 /** 
     * preHandle方法是进行处理器拦截用的，该方法将在Controller处理之前进行调用，
     * 该方法的返回值为true拦截器才会继续往下执行，该方法的返回值为false的时候整个请求就结束了。 
     */  
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("preHandle-->");
		boolean flag=false;
		//获取请求的路径进行判断
		String servletPath=request.getServletPath();
		for(String s:IGNORE_URI){
			if(servletPath.contains(s)){
				flag=true;
				break;
			}
		}
		// 拦截请求
		if(!flag){
			Admin admin=(Admin)request.getSession().getAttribute("admin");
			if(admin==null){
				System.out.println("AuthorizationInterceptor拦截请求!");
				request.setAttribute("message", "请先登录!");
				request.getRequestDispatcher("loginForm").forward(request, response);
			}else {
				System.out.println("放行请求");
				flag=true;
			}
		}
		return flag;
	}

}
