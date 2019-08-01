package com.nowcoder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import com.nowcoder.dao.CommentDao;
import com.nowcoder.model.Comment;

@Service
public class CommentService {
	 @Autowired
	  CommentDao commentDao;
	 
	 @Autowired
	 SensitiveService sensitiveService;
	 public List<Comment> selectCommentById( int entityId,int entityType){
		 return commentDao.selectCommentById(entityId, entityType);
	 }
	 
	 public int getCommentCount(int entityId,int entityType) {
		 return commentDao.getCommentCount(entityId, entityType);
	 }
	 
	 
	 public int addComment(Comment comment) {
		 comment.setContent(HtmlUtils.htmlEscape(comment.getContent()));
		 comment.setContent(sensitiveService.filter(comment.getContent()));
		 return commentDao.addComment(comment);
	 }
	 
	 public boolean deleteComment(int commentId,int status) {
		 return commentDao.deleteComment(commentId, 1)>0;
	 }
	 public Comment getCommentById(int id) {
		 return commentDao.getCommentById(id);
	 }
}
