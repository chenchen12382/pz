package com.fh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.stereotype.Service;

import com.fh.base.AssertUtil;
import com.fh.dao.UserDao;
import com.fh.exception.ParamException;
import com.fh.model.User;
import com.fh.util.MD5Util;
import com.fh.vo.UserLoginIdentity;
import com.fh.util.UserIDBase64;
 
@Service
public class UserService {

	@Autowired																																			
	private UserDao userDao;

	public UserLoginIdentity login(String userName, String password) {
		// 非空验证
		AssertUtil.isNotEmpty(userName, "请输入用户名");
		AssertUtil.isNotEmpty(password, "请输入密码");

		// 根据用户名查询用户在验证
		User user = userDao.findByUserName(userName.trim());

		AssertUtil.notNull(user);
		if (!MD5Util.md5Method(password).equals(user.getPassword())) {
			throw new ParamException(103, "用户名或密码错误,请重新输入");
		}
		UserLoginIdentity userLoginIdentity = buildLoginIdentity(user);
		return userLoginIdentity;

	}

	/**
	 * 构建登录信息
	 * 
	 * @param user
	 * @return
	 */
	private static UserLoginIdentity buildLoginIdentity(User user) {
		UserLoginIdentity userLoginIdentity = new UserLoginIdentity();
		userLoginIdentity.setUserIdString(UserIDBase64.encoderUserID(user.getId()));
		userLoginIdentity.setRealName(user.getTrueName());
		userLoginIdentity.setUserName(user.getUserName());
		return userLoginIdentity;
	}
	
	
	/**
	 * 获取用户登录信息
	 * @param userId
	 * @return
	 */
	public UserLoginIdentity findLoginUser(Integer userId) {
		if(userId==null||userId<1){
			throw new ParamException(100,"请登录");
		}
		User user = userDao.findById(userId);
		UserLoginIdentity userLoginIdentity = buildLoginIdentity(user);
		return userLoginIdentity;
	}

}
