package com.nowcoder.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nowcoder.model.HostHolder;
import com.nowcoder.model.Question;
import com.nowcoder.model.ViewObject;
import com.nowcoder.service.QuestionService;
import com.nowcoder.service.UserService;

@Controller
public class LoginController {
	private static final Logger logger=LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private UserService userService;
	@Autowired
	private QuestionService questionService;
	@Autowired
	private HostHolder hostHolder;
	
	
	@RequestMapping(path= {"/reg"})
	public String register(Model model,
			@RequestParam("username") String username,
			@RequestParam(value="next",required=false) String next,
			@RequestParam("password") String password,
			HttpServletResponse response) {
	
		try {
			Map<String,String> userMap=userService.register( username, password);
			if(userMap.containsKey("ticket")) {
				String ticket=userMap.get("ticket");
				Cookie cookie=new Cookie("ticket",ticket);
				cookie.setPath("/");
				response.addCookie(cookie);
				if(!next.isEmpty()) {
					return "redirect:"+next;
				}
				return "redirect:/";
			}else  {
				model.addAttribute("msg",userMap.get("msg"));
				return "login";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("注册失败"+e.getMessage());
			return "login";
		}
	}
	
	 @RequestMapping(path = {"/user/{userId}"})
	    public String userIndex(Model model, @PathVariable("userId") int userId) {
		   List<ViewObject> vos=new ArrayList<ViewObject>();
		   List<Question> listQuestion=questionService.getLatestQuestion(userId, 0, 10);
		   for(Question question:listQuestion) {
		   ViewObject vo=new ViewObject();
		   vo.set("question",question); 
		   vo.set("user",userService.selectUserById(hostHolder.getUser().getId())); 
	       vos.add(vo);
		   }
		   model.addAttribute("vos", vos);
	        return "index";
	    }
	 
	@RequestMapping(path= {"/login"})
	public String login(Model model,
			@RequestParam("username") String username,
			@RequestParam("password") String password,
			@RequestParam(value="next",required=false) String next,
			@RequestParam(value="rememberme",defaultValue="false") boolean rememberme,
			HttpServletResponse response) {
		try {
			Map<String,String> userMap=userService.login( username, password);
			if(userMap.containsKey("ticket")) {
				String ticket=userMap.get("ticket");
				Cookie cookie=new Cookie("ticket",ticket);
				cookie.setPath("/");
				response.addCookie(cookie);
				if(!next.isEmpty()) {
					return "redirect:"+next;
				}
				return "redirect:/";
			}else {
				model.addAttribute("msg",userMap.get("msg"));
				return "login";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("登陆失败"+e.getMessage());
			return "login";
		}
		
	}
	

	@RequestMapping(path= {"/logout"})
	public String loginout(@CookieValue("ticket") String ticket){
		userService.logout(ticket);
		return "redirect:/";
		
	}
	
	@RequestMapping(path= {"/reglogin"})
	public String register(Model model,
			@RequestParam(value="next",required=false) String next) {
		model.addAttribute("next",next);
		return "login";
	}
}
