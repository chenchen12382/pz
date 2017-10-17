package com.fh.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fh.dao.UserRoleDao;
import com.fh.model.UserRole;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.stereotype.Service;

import com.fh.base.AssertUtil;
import com.fh.dao.UserDao;
import com.fh.dto.UserQuery;
import com.fh.exception.ParamException;
import com.fh.model.User;
import com.fh.util.MD5Util;
import com.fh.vo.UserLoginIdentity;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.fh.util.UserIDBase64;
 
@Service
public class UserService {

	@Autowired																																			
	private UserDao userDao;

	@Autowired
	private UserRoleDao userRoleDao;

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
	
	/**
	 * 列表展示
	 * @param query
	 * @return
	 */
	public Map<String, Object> selectForPage(UserQuery query) {
		PageList<User> users = userDao.selectForPage(query, query.buildPageBounds());
		//		for(User user : users) {
		//			//
		//		}
		Map<String, Object> result = new HashMap<>();
		result.put("rows", users);
		result.put("total", users.getPaginator().getTotalCount());
		return result;
		
	}

	/**
	 * 新增
	 * @param user
	 */
    public void add(User user) {
//    	AssertUtil.isNotEmpty(user.getUserName(),"请输入用户名");
//    	AssertUtil.isNotEmpty(user.getPassword(),"请输入密码");
//    	AssertUtil.isNotEmpty(user.getTrueName(),"请输入真实姓名");
//    	AssertUtil.isNotEmpty(user.getPhone(),"请填写联系电话");

		// 非空验证 用户名 密码 真实姓名 电话 邮箱 角色
		checkParams(user);
		String userName = user.getUserName();
		// 用户名唯一验证
		User userByUserName = userDao.findByUserName(userName);
		AssertUtil.isTrue(userByUserName != null, "该用户名已存在");


		User findByPhone = userDao.findByPhone(user.getPhone());
		AssertUtil.isTrue(findByPhone != null , "该手机号已存在");
		String password = user.getPassword();
		// 密码加密
		password = MD5Util.md5Method(password);
		user.setPassword(password);
		// 插入数据库
		userDao.insert(user);
		// 关联角色
		saveUserRoles(user);


//
//		checkParams(user);
//		String username = user.getUserName();
//		User findByUserName = userDao.findByUserName(username);
//		AssertUtil.isTrue(findByUserName != null,"该用户名已存在");
//		//邮箱 手机号验证

//
////		if(user.getEmail().equals(findByUserName.getEmail())){
////			throw new ParamException("该邮箱已存在");
////		}
////		if(user.getPhone().equals(findByUserName.getPhone())){
////			throw new ParamException("该联系电话已存在");
////		}
//
//		String password=MD5Util.md5Method(user.getPassword());
//		user.setPassword(password);
//		userDao.add(user);
////		User selectForUpdate=userDao.findByUserName(user.getUserName());
//
//		//关联角色
//		saveUserRoles(user);

    }

	/**
	 * 更新用户信息
	 * @param user
	 */
	public void update(User user) {
//		AssertUtil.isNotEmpty(user.getUserName(),"请输入用户名");
//		AssertUtil.isNotEmpty(user.getPassword(),"请输入密码");
//		AssertUtil.isNotEmpty(user.getTrueName(),"请输入真实姓名");
//		AssertUtil.isNotEmpty(user.getPhone(),"请填写联系电话");
		AssertUtil.intIsNotEmpty(user.getId(),"请选择记录进行修改");
		checkParams(user);
		User userFromDB = userDao.findById(user.getId());
		//用户名唯一验证
		if(!userFromDB.getUserName().equals(user.getUserName())){
			User findByUserName = userDao.findByUserName(user.getUserName());
			AssertUtil.isTrue(findByUserName != null , "该用户名已存在");
		}

		if(!userFromDB.getPhone().equals(user.getPhone())){
			AssertUtil.isTrue(userDao.findByPhone(user.getPhone()) != null,"该手机已存在");
		}

		String password = MD5Util.md5Method(user.getPassword());
		user.setPassword(password);

//		// 邮箱、手机号唯一验证 TODO
//		User findByEmail = userDao.findByEmail(user.getEmail());
//		AssertUtil.isTrue(findByEmail != null,"该邮箱已存在");
//
//		User findByPhone = userDao.findByPhone(user.getPhone());
//		AssertUtil.isTrue(findByPhone != null , "该手机号已存在");

    // 更新
		userDao.update(user);
		// 关联角色
		// 先删除 在关联
		userRoleDao.deleteUserRoles(user.getId());
		saveUserRoles(user);
	}

	/**
	 * 批量删除
	 * @param ids
	 */
	public void deleteBatch(String ids) {
		if(StringUtils.isBlank(ids)){
			throw new ParamException("请选择用户进行删除！");
		}

		userDao.deleteBatch(ids);
	}


	/**
	 * 基本参数验证
	 * @param user
	 */
	private static void checkParams(User user) {
		String userName = user.getUserName();
		AssertUtil.isNotEmpty(userName, "请输入用户名");
		String password = user.getPassword();
		AssertUtil.isNotEmpty(password, "请输入密码");
		String realName = user.getTrueName();
		AssertUtil.isNotEmpty(realName, "请输入真实姓名");
		String phone = user.getPhone();
		AssertUtil.isNotEmpty(phone, "请输入手机号");

		Integer[] roleIds = user.getRoleIds();
		AssertUtil.isTrue(roleIds == null || roleIds.length == 0, "请选择角色");
	}

	/**
	 * 插入角色
	 * @param user
	 */
	private void saveUserRoles(User user) {
		List<UserRole> userRoles = new ArrayList<>();
		for (Integer roleId : user.getRoleIds()) {
			UserRole userRole = new UserRole();
			userRole.setRoleId(roleId);
			userRole.setUserId(user.getId());
			userRoles.add(userRole);
		}
		userRoleDao.insertBatch(userRoles);
	}


    public void updatePassword(int userId, String oldPassword, String newPassword, String confirmPassword) {

		// 基本参数校验
		AssertUtil.isTrue(userId == 0, "请重新登陆");
		AssertUtil.isTrue(StringUtils.isBlank(oldPassword), "请输入旧密码");
		AssertUtil.isTrue(StringUtils.isBlank(newPassword), "请输入新密码");
		AssertUtil.isTrue(StringUtils.isBlank(confirmPassword), "请输入确认密码");
		AssertUtil.isTrue(!newPassword.equals(confirmPassword), "新密码和确认密码不一致, 请重新输入");

		// 查询用户判断旧密码输入是否正确
		User user = userDao.findById(userId);
		AssertUtil.notNull(user, "该用户不存在或已被注销");
		String password = MD5Util.md5Method(oldPassword);
		AssertUtil.isTrue(!password.equals(user.getPassword()), "旧密码输入错误, 请重新输入");
		// 更新
		String newPwd = MD5Util.md5Method(newPassword);
		int mnt = userDao.updatePassword(user.getId(), newPwd);
		AssertUtil.isTrue(mnt == 0, "更新失败, 请重试");

    }
}
