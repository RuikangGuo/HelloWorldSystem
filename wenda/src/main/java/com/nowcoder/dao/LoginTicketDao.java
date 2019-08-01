package com.nowcoder.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.nowcoder.model.LoginTicket;

@Mapper
public interface LoginTicketDao {
	 String Table_Name=" login_ticket ";
	 String Insert_Fields=" user_id, expired, status, ticket  ";
	 String Select_Fields=" id, "+Insert_Fields;
	 
	 @Insert({"insert into",Table_Name," (",Insert_Fields,") values(#{userId},#{expired},#{status},#{ticket})"})
	 int addTicket(LoginTicket ticket);
	 
	 @Select({"select ",Select_Fields," from ",Table_Name," where ticket=#{ticket}"})
	 LoginTicket  selectByTicket(String ticket);
	 
	 @Update({"update  ",Table_Name," set status=#{status} where ticket=#{ticket}"})
	 void  updateStatus(@Param("ticket") String ticket,
			 @Param("status")  int status);
}