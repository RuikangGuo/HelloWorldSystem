package com.nowcoder.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.nowcoder.model.HostHolder;

@Component
public class LoginRequestInterceptor implements HandlerInterceptor{

	
	@Autowired
	private HostHolder hostHolder;
	
	
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		
	}

	@Override
	public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object arg2) throws Exception {
	   if(hostHolder.getUser()==null) {
		   //httpServletRequest.getRequestURI() 为当前访问的页面
		   System.out.println("hahh"+httpServletRequest.getRequestURI());
		   httpServletResponse.sendRedirect("/reglogin?next="+httpServletRequest.getRequestURI());
	   }
		return true;
	}

}
