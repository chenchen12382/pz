package com.fh.controller;

import java.util.Map;

import com.fh.annotation.RequirePermissions;
import com.fh.exception.ParamException;
import com.fh.util.LoginUserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fh.base.BaseController;
import com.fh.base.ResultInfo;
import com.fh.dto.UserQuery;
import com.fh.model.User;
import com.fh.service.UserService;
import com.fh.vo.UserLoginIdentity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("user")
public class UserController extends BaseController{
	
	@Autowired
	private UserService userService; 
	
	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	
	@RequestMapping("login")
	@ResponseBody
	public ResultInfo login(String userName, String password, String code, HttpSession session){
		//验证码
//		String code=request.getParameter("code");
		if (!(code.equalsIgnoreCase(session.getAttribute("code").toString()))) {  //忽略验证码大小写
			throw new ParamException("验证码错误,请重新输入");
		}
		
		logger.info("這是一個參數：userName={}, password={}", userName, password);
		UserLoginIdentity userLoginIdentity = userService.login(userName,password);
		return success(userLoginIdentity);
	}
	
	@RequestMapping("index")
	public String index(){
		return "user";
	}

	@RequirePermissions(permission = "9010")
	@RequestMapping("list")
	@ResponseBody
	public Map<String, Object>selectForPage(UserQuery query) {
		Map<String, Object> result = userService.selectForPage(query);
		return result;
	}
	
	@RequestMapping("add")
	@ResponseBody
	public ResultInfo add(User user) {
		userService.add(user);
		return success("添加成功");
	}

	@RequestMapping("update")
	@ResponseBody
	public ResultInfo update(User user) {
		userService.update(user);
		return success("修改成功");
	}

	@RequestMapping("delete")
	@ResponseBody
	public ResultInfo delete(String ids) {
		userService.deleteBatch(ids);
		return success("删除成功");
	}

	@RequestMapping("update_password")
	public @ResponseBody Object updatePassword(String oldPassword,
											   String newPassword, String confirmPassword, HttpServletRequest request) {
		int userId = LoginUserUtil.releaseUserIdFromCookie(request);
		userService.updatePassword(userId, oldPassword, newPassword, confirmPassword);
		return success("更新成功, 系统将自动退出, 请重新登陆");
	}

	
}
