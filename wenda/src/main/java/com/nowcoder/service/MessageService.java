package com.nowcoder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nowcoder.dao.MessageDao;
import com.nowcoder.model.Message;

@Service
public class MessageService {
	@Autowired
	private MessageDao messageDao;
	
	 @Autowired
	 SensitiveService sensitiveService;
	
	 public int getConversationUnreadCount(String conversationId, int userId) {
		 return messageDao.getConversationUnreadCount(conversationId, userId);
	 }
	 
   public int addMessage(Message message) {
	   message.setContent(sensitiveService.filter(message.getContent()));
	   return messageDao.addMessage(message)>0? message.getId() : 0;
   }
   
   public  List<Message> getConversationList( int userId,int offset, int limit){
	   return messageDao.getConversationList(userId, offset, limit);
   }
   
   public  List<Message> getConversationDetail( String conversationId,int offset,int limit){
	   return messageDao.getConversationDetail(conversationId, offset, limit);
   }
   
}
