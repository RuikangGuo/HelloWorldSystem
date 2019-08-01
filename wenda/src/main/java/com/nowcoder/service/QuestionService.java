package com.nowcoder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import com.nowcoder.dao.QuestionDao;
import com.nowcoder.model.Question;

@Service
public class QuestionService {
	
	  @Autowired 
	private QuestionDao questionDao;
	
	  @Autowired 
	  private SensitiveService sensitiveService;
	  
	 
	/*
	 * public void addQuestion(Question question ) {
	 * questionDao.addQuestion(question); }
	 */
	public List<Question> getLatestQuestion(int userId, int offset,int limit) {
		return questionDao.getLatestQuestion(userId, offset, limit);
	}
	
	public Question selectById(int id) {
		return questionDao.selectById(id);
	}
	
	public int  addQuestion(Question question) {
		////敏感词过滤
		//html过滤
		question.setTitle(HtmlUtils.htmlEscape(question.getTitle())); 
		question.setContent(HtmlUtils.htmlEscape(question.getContent()));
		//敏感词过滤
		question.setTitle(sensitiveService.filter(question.getTitle())); 
		question.setContent(sensitiveService.filter(question.getContent()));
		
		
		return (questionDao.addQuestion(question)>0 ? question.getId():0);
	}
	
	public int updateCommentCount(int id, int commentId ) {
		return questionDao.updateCommentCount(id, commentId);
	}
	
}
