package com.nowcoder.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nowcoder.model.Comment;
import com.nowcoder.model.EntityType;
import com.nowcoder.model.HostHolder;
import com.nowcoder.service.CommentService;
import com.nowcoder.service.QuestionService;
import com.nowcoder.util.WendaUtil;

@Controller
public class CommentController {
	private static final Logger logger=LoggerFactory.getLogger(CommentController.class);
	   @Autowired
	   CommentService  commentService;
	   @Autowired
	   HostHolder  hostHolder;
	   @Autowired
	   QuestionService  questionService;
   
	   @RequestMapping(path= {"/addComment"})
	   public String addComment(@RequestParam("questionId") int questionId,
			              @RequestParam("content")  String content  ) {
		try {
		   Comment comment=new Comment();
		   comment.setContent(content);
		   comment.setCreatedDate(new Date());
		   comment.setEntityId(questionId);
		   comment.setEntityType(EntityType.ENTITY_QUESTION);
		   comment.setStatus(0);
		   if(hostHolder.getUser()!=null) {
			   comment.setUserId(hostHolder.getUser().getId());
		   }else {
			   comment.setUserId(WendaUtil.ANONYMOUS_USERID);
			   //return "redirect:/reglogin";
		   }
		   commentService.addComment(comment);
		   int count=commentService.getCommentCount(comment.getEntityId(), comment.getEntityType());
		   questionService.updateCommentCount(comment.getEntityId(), count);
		   
		}catch(Exception e) {
			logger.error("增加评论失败"+e.getMessage());
		}
		   return "redirect:/question/"+String.valueOf(questionId);
	   }
}
