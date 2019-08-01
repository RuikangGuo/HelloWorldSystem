package com.nowcoder.interceptor;
import java.util.Date;

import javax.servlet.http.Cookie;
/**
 * 页面访问状态，是否已经登陆，或者是未登录状态
 * 为了构建一个链路上随时可用的HostHolder
 */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.nowcoder.dao.LoginTicketDao;
import com.nowcoder.dao.UserDao;
import com.nowcoder.model.HostHolder;
import com.nowcoder.model.LoginTicket;
import com.nowcoder.model.User;

@Component
public class PassportInterceptor implements HandlerInterceptor {

	@Autowired
    private LoginTicketDao loginTicketDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private HostHolder hostHolder;
	
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		hostHolder.clear();
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView modelAndView)
			throws Exception {
		if(modelAndView!=null) {
		modelAndView.addObject("user", hostHolder.getUser());
	}
	}

	@Override
	public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object arg2) throws Exception {
		String ticket=null;
		if(httpServletRequest.getCookies()!=null) {
		for (Cookie	 cookie : httpServletRequest.getCookies()) {
			if(cookie.getName().equals("ticket")) {
				ticket =cookie.getValue();
				break;
			}
		}
	 if(ticket!=null) {
	  LoginTicket loginTicket=loginTicketDao.selectByTicket(ticket);
	  if(loginTicket==null||loginTicket.getStatus()!=0||loginTicket.getExpired().before(new Date())) {
		  return true;
	  }
	  //如果ticket真实有效的存在，将ticket关联的用户信息取出来，放置上下文，以后的所有的链路都可以访问这个上下文
		 User user= userDao.selectUserById(loginTicket.getUserId());
		  hostHolder.setUser(user);
	}
	}
		return true;
	}
   
}
