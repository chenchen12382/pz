package com.fh.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.fh.model.User;

public interface UserDao {
	
	
	
	/**
	 * 通过用户名查找User对象
	 * @param trim
	 * @return
	 */
	@Select("select id, user_name, password, true_name, email, phone from t_user "
			+ "where user_name = #{userName} and is_valid = 1")
	User findByUserName(@Param(value="userName")String userName);
	
	@Select("select id, user_name, password, true_name, email, phone from t_user where id = #{userId}")
	User findById(@Param(value="userId")Integer userId);

}
