package com.fh.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.fh.dto.UserQuery;
import com.fh.model.User;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

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

	PageList<User> selectForPage(UserQuery query, PageBounds buildPageBounds);

	@Insert("insert into t_user  (user_name,password,true_name,email,phone,is_valid,create_date,update_date) " +
			" values (#{userName},#{password},#{trueName},#{email},#{phone},1,now(),now() )")
    void add(User user);
}
