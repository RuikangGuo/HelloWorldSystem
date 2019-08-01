package com.nowcoder.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nowcoder.dao.LoginTicketDao;
import com.nowcoder.dao.UserDao;
import com.nowcoder.model.LoginTicket;
import com.nowcoder.model.User;
import com.nowcoder.util.WendaUtil;

@Service
public class UserService {
	
	  @Autowired
	  private UserDao userDao;
	  @Autowired
	 private LoginTicketDao loginTicketDao;
	  
	public void addUser(User user) {
		 userDao.addUser(user); 
   }
	
	
	public Map<String, String> register(String username,String password) {
		Map<String, String> map=new HashMap<String,String>();
		
		if(username.isEmpty()) {
			map.put("msg", "用户名不能为空");
			return map;
		}
		if(password.isEmpty()) {
			map.put("msg", "密码不能为空");
			return map;
		}
		User user=userDao.selectUserByName(username);
		if(user!=null) {
			map.put("msg","用户名已经被注册");
		}
		user=new User();
		user.setName(username);
		user.setSalt(UUID.randomUUID().toString().substring(0,5));
		Random random=new Random();
		user.setHeadUrl(String.format("http://images.nowcoder.com/head/%dt.png", random.nextInt(1000)));
		user.setPassword(WendaUtil.MD5(password+user.getSalt()));
		userDao.addUser(user);
		String ticket=addLoginTicket(user.getId());
		map.put("ticket", ticket);
		return map;
	}
	
	
	public Map<String, String> login(String username,String password) {
		Map<String, String> map=new HashMap<String,String>();
		if(username.isEmpty()) {
			map.put("msg", "用户名不能为空");
			return map;
		}
		if(password.isEmpty()) {
			map.put("msg", "密码不能为空");
			return map;
		}
		User user=userDao.selectUserByName(username);
		if(user==null) {
			map.put("msg","用户名不存在");
		}
		if(!WendaUtil.MD5(password+user.getSalt()).equals(user.getPassword())){
			map.put("msg","密码错误");
			return map;
		};
		String ticket=addLoginTicket(user.getId());
		map.put("ticket", ticket);
		return map;
	}
	
	public String addLoginTicket(int userId) {
		LoginTicket loginTicket  =new LoginTicket();
		loginTicket.setUserId(userId);
		loginTicket.setStatus(0);
		Date now =new Date();
		now.setTime(3600*24*100+now.getTime());
		loginTicket.setExpired(now);
		loginTicket.setTicket(UUID.randomUUID().toString().replaceAll("-", ""));
		loginTicketDao.addTicket(loginTicket);
		return loginTicket.getTicket();
	}
	
	public void logout(String ticket) {
		loginTicketDao.updateStatus(ticket, 1);
		
	}
	
	public  User selectUserById(int id){
		return userDao.selectUserById(id);
	}
	
	 public User selectByName(String name) {
	        return userDao.selectUserByName(name);
	    }
}
