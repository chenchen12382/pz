package com.fh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fh.base.BaseController;
import com.fh.base.ResultInfo;
import com.fh.service.UserService;
import com.fh.vo.UserLoginIdentity;

@Controller
@RequestMapping("user")
public class UserController extends BaseController{

	private UserService userService; 
	
	@RequestMapping("login")
	public ResultInfo login(String userName, String password){
		UserLoginIdentity userLoginIdentity = userService.login(userName,password);
		
		return success(userLoginIdentity);
	}
	
	
	
}
