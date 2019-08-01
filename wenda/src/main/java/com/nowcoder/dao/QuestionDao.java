package com.nowcoder.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.nowcoder.model.Question;
@Mapper
public interface QuestionDao {
	 String Table_Name=" question ";
	 String Insert_Fields=" title, content, user_id, created_time,comment_count ";
	 String Select_Fields=" id, "+Insert_Fields;
	 
	@Insert({"insert into",Table_Name," (",Insert_Fields,") values(#{title},#{content},#{userId},#{createdTime},#{commentCount})"})
	int addQuestion(Question question);
	
	@Select({"select ",Select_Fields," from ",Table_Name," where id=#{id}"})
    Question  selectById(int id);
	
	List<Question> getLatestQuestion(@Param("userId") int userId,
			                           @Param("offset") int offset,
			                             @Param("limit") int limit);
	
	@Update({"update ",Table_Name,"set comment_count=#{commentCount} where id=#{id}"})
	int updateCommentCount(@Param("id") int id, @Param("commentCount") int commentCount);
}
