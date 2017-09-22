package com.fh.service;

import java.util.List;

import com.fh.base.AssertUtil;
import com.fh.dao.UserRoleDao;
import com.fh.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserRoleService {
	
	@Autowired
	private UserRoleDao userRoleDao;

	public List<UserRole> findUserRoles(Integer userId) {
		AssertUtil.intIsNotEmpty(userId, "请选择用户");
		List<UserRole> userRoles = userRoleDao.findUserRoles(userId);
		return userRoles;
	}

}
