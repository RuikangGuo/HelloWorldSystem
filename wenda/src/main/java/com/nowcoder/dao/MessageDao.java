package com.nowcoder.dao;
import java.util.List;

/**
 * 	private int id;
	private int fromId;
	private int toId;
	private String content;
	private Date createdDate;
	private int hasRead;
	private String conversationId;
 */
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.nowcoder.model.Message;
@Mapper
public interface MessageDao {
	 String Table_Name=" message ";
	 String Insert_Fields=" from_id, to_id, created_date,content,has_read ,conversation_id";
	 String Select_Fields=" id, "+Insert_Fields;
	 
	 @Insert({"insert into",Table_Name," (",Insert_Fields,") values(#{fromId},#{toId},#{createdDate},#{content},#{hasRead},#{conversationId})"})
	 int addMessage(Message message);
	 
	 @Select({"select ",Select_Fields," from ",Table_Name," "
		 		+ "where conversation_id=#{conversationId}  order by created_date desc limit #{offset},#{limit}"})
		 List<Message> getConversationDetail( @Param("conversationId") String conversationId,
												 @Param("offset") int offset,
												 @Param("limit") int limit);
	 
	 //select *,count(id) from (select * from message order by created_date desc) tt group  by conversation_id oder by  created_date desc
	 @Select({"select ",Insert_Fields," , count(id) as id from (select * from ",Table_Name,"where from_id=#{userId} or to_id=#{userId} order by created_date desc) tt  group by  conversation_id order by  created_date desc limit #{offset},#{limit}"})
	 List<Message> getConversationList( @Param("userId") int userId,
										 @Param("offset") int offset,
										 @Param("limit") int limit);
	 
	 @Select({"select count(id) from ",Table_Name," "
		 		+ "where has_read=0  and to_id=#{userId} and conversation_id=#{conversationId}"}) 
	 int getConversationUnreadCount( @Param("conversationId") String conversationId,
			                          @Param("userId") int userId);
	 
	 
	 
	 
	 
	 
	 
	 
}
