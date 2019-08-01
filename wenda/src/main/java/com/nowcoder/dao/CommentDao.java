package com.nowcoder.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.nowcoder.model.Comment;
@Mapper
public interface CommentDao {
	 String Table_Name=" comment ";
	 String Insert_Fields=" user_id, content, created_date,entity_id,entity_type ,status";
	 String Select_Fields=" id, "+Insert_Fields;
	 
	 @Insert({"insert into",Table_Name," (",Insert_Fields,") values(#{userId},#{content},#{createdDate},#{entityId},#{entityType},#{status})"})
	 int addComment(Comment comment);
	 
	 @Select({"select ",Select_Fields," from ",Table_Name," "
	 		+ "where entity_id=#{entityId} and entity_type=#{entityType} order by created_date desc"})
	 List<Comment> selectCommentById( @Param("entityId") int entityId,
                                    @Param("entityType") int entityType);
	 
	 
	 @Select({"select count(id) from ",Table_Name," "
		 		+ "where entity_id=#{entityId} and entity_type=#{entityType}"})
	 int getCommentCount(@Param("entityId") int entityId,
                         @Param("entityType") int entityType);
	 
	 @Update({"update comment set status=#{status} where id=#{id} "})
     int  deleteComment(@Param("id") int id, @Param("status") int status); 
	 
	 @Select({"select ",Select_Fields," from ",Table_Name," where id=#{id}" })
	   Comment getCommentById(int id);
}
