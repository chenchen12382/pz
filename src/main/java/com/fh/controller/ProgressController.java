package com.fh.controller;

import java.awt.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fh.dto.ProgressQuery;
import com.fh.service.ProgressService;


/**
 * 业务管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping("progress")
public class ProgressController {
	
	@Autowired
	private ProgressService progressService;
	
	/**
	 * 定位
	 * @return
	 */
	@RequestMapping("index")
	public String index() {
		return "progress";
	}
	
	@RequestMapping("list")
	@ResponseBody
	public Map<String, Object> SelectForPage(ProgressQuery query) {
		
		Map<String, Object> result = progressService.selectForPage(query);
		
		return result;
		
	}
	
	
	
}

