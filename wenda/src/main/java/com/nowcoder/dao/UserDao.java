package com.nowcoder.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.nowcoder.model.User;

@Mapper
public interface UserDao {
	 String Table_Name=" user ";
	 String Insert_Fields=" name, password, salt, head_url ";
	 String Select_Fields=" id, "+Insert_Fields;
	 
	@Insert({"insert into",Table_Name," (",Insert_Fields,") values(#{name},#{password},#{salt},#{headUrl})"})
	int addUser(User user);
	
	@Select({"select ",Select_Fields," from ",Table_Name," where id=#{id}"})
    User  selectUserById(int id);
	
	@Select({"select ",Select_Fields," from ",Table_Name," where name=#{name}"})
	User  selectUserByName(String name);
	
	
}

