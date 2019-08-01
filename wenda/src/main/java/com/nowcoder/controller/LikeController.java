package com.nowcoder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nowcoder.async.EventModel;
import com.nowcoder.async.EventProducer;
import com.nowcoder.async.EventType;
import com.nowcoder.model.Comment;
import com.nowcoder.model.EntityType;
import com.nowcoder.model.HostHolder;
import com.nowcoder.service.CommentService;
import com.nowcoder.service.LikeService;
import com.nowcoder.util.WendaUtil;

@Controller
public class LikeController {
	@Autowired
	HostHolder  hostHolder;
	@Autowired
	LikeService  likeService;
	@Autowired
	CommentService  commentService;
	@Autowired
	EventProducer  eventProducer;
	
	  @RequestMapping(path= {"/like"},method= {RequestMethod.POST})
	  @ResponseBody
	   public String like(@RequestParam("commentId") int commentId) {
		  if(hostHolder.getUser()==null) {
			  return WendaUtil.getJSONString(999);
		  }
	
		  Comment comment=commentService.getCommentById(commentId);
		  
		  eventProducer.fireEvent(new EventModel(EventType.LIKE)
				  .setActorId(hostHolder.getUser().getId()).setEntityId(commentId).setEntityOwnerId(comment.getUserId())
				  .setEntityType(EntityType.ENTITY_COMMENT).setExt("questionId",String.valueOf(comment.getEntityId())));
		  
		  
		  long likeCount =likeService.like(hostHolder.getUser().getId(), EntityType.ENTITY_COMMENT, commentId);
		  return WendaUtil.getJSONString(0, String.valueOf(likeCount));
	  }
	  
	  
	  @RequestMapping(path= {"/dislike"},method= {RequestMethod.POST})
	  @ResponseBody
	  public String dislike(@RequestParam("commentId") int commentId) {
		  if(hostHolder.getUser()==null) {
			  return WendaUtil.getJSONString(999);
		  }
		  long dislikeCount =likeService.dislike(hostHolder.getUser().getId(), EntityType.ENTITY_COMMENT, commentId);
		  return WendaUtil.getJSONString(0, String.valueOf(dislikeCount));
	  }
	  
	  
	  
}
