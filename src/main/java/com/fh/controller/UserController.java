package com.fh.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fh.base.BaseController;
import com.fh.base.ResultInfo;
import com.fh.service.UserService;
import com.fh.vo.UserLoginIdentity;


@Controller
@RequestMapping("user")
public class UserController extends BaseController{
	
	@Autowired
	private UserService userService; 
	
	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	
	@RequestMapping("login")
	@ResponseBody
	public ResultInfo login(String userName, String password){
		
		logger.info("這是一個參數：userName={}, password={}", userName, password);
		UserLoginIdentity userLoginIdentity = userService.login(userName,password);
		return success(userLoginIdentity);
	}
}
