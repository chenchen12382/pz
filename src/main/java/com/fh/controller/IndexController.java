package com.fh.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fh.base.BaseController;
import com.fh.service.UserService;
import com.fh.util.LoginUserUtil;
import com.fh.vo.UserLoginIdentity;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController extends BaseController{
	
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("index")
	public String index() {
		
		return "index";
	}
	
	@RequestMapping("main")
	public String main(Model model,HttpServletRequest request){
		// 获取登录用户的信息
				Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
				UserLoginIdentity userLoginIdentity = userService.findLoginUser(userId);
				model.addAttribute("currentUser", userLoginIdentity);
				return "main";
	}

	@RequestMapping("mobile_main")
	public String mobileMain(Model model,HttpServletRequest request){
		// 获取登录用户的信息
		Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
		UserLoginIdentity userLoginIdentity = userService.findLoginUser(userId);
		model.addAttribute("currentUser", userLoginIdentity);
		return "mobile_main";
	}




	@RequestMapping("test")
	@ResponseBody
	public Map<String, Object> clintTest(String name,String password){
		Map<String,Object> result = new HashMap<>();
		Map<String,Object> date = new HashMap<>();
		List list = new ArrayList();
		date.put("parkId",1);
		date.put("parkName","B1停车场");
		date.put("totalNum",100);
		date.put("freeSpaceNum",80);
		list.add(date);
		result.put("resCode",1);
		result.put("resMag","");
		result.put("data",list);
		return result;

	}


}
