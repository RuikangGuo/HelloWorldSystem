package com.nowcoder.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nowcoder.model.Question;
import com.nowcoder.model.User;
import com.nowcoder.model.ViewObject;
import com.nowcoder.service.QuestionService;
import com.nowcoder.service.UserService;

@Controller
public class IndexController {
	
	  @Autowired
	  private UserService userService;
	  @Autowired
	  private QuestionService questionService;
	  
	@RequestMapping(path= {"/","/index"},method= {RequestMethod.GET})
	public String index(Model model) {
		
		List<Question> questionList= questionService.getLatestQuestion(0,0,10);
		List<ViewObject> vos= new ArrayList<ViewObject>();
		for(Question question:questionList) {
			ViewObject vo=new ViewObject();
			vo.set("question",question);
			vo.set("user" ,userService.selectUserById(question.getUserId()));
		     vos.add(vo);
		}
	
		model.addAttribute("vos",vos);

		return "index";
	}
	   
	
	
	
	
	@RequestMapping(path= {"/profile/{userId}"})
	@ResponseBody
	public String profile(@PathVariable("userId") int userId,
			@RequestParam(value="  type",defaultValue="1") int type,
		@RequestParam(value="key",defaultValue="Zz", required=false) String key) {
		return String.format("Profile Page of %d,t:%d k:%s",userId,type,key);
	}
	@RequestMapping(path= {"/ftl"})
	public String template() {
		return "home";
	}
	
	@RequestMapping(path= {"/addUser"})
	public void addUser() {
		Random random =new Random();
		for(int i=0;i<30;i++) {
			User user=new User();
			user.setHeadUrl(String.format("http://images.nowcoder.com/head/%dt.png",random.nextInt(1000)));
			user.setName(String.format("User%d",i));
			user.setSalt(String.format("jiami%d",random.nextInt(100)));
			user.setPassword(String.format("mima%d",random.nextInt(100)));
			userService.addUser(user);
		}
		}
	
	@RequestMapping(path= {"/addQuestion"})
	public void addQuestion() {
		Random random =new Random();
		for(int i=0;i<31;i++) {
			Question question=new Question();
			question.setCommentCount(i);
			question.setTitle(String.format("title%d",i));
			question.setUserId(i+1);
			question.setContent(String.format("	blalalaalalcontent%d",random.nextInt(100)));
			Date data=new Date();
			data.setTime(data.getTime()+ 360*10000*i);
			question.setCreatedTime(data);
			questionService.addQuestion(question);
		}
		}
	

}
