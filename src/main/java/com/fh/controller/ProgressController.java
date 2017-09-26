package com.fh.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.fh.annotation.RequirePermissions;
import com.fh.model.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fh.base.BaseController;
import com.fh.base.Constant;
import com.fh.base.ResultInfo;
import com.fh.dto.ProgressQuery;
import com.fh.model.Progress;
import com.fh.service.ProgressService;


/**
 * 进度管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping("progress")
public class ProgressController extends BaseController{
	
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
	
	
	/**
	 * 分页模糊查询
	 * @param query
	 * @return
	 */
	@RequirePermissions(permission = "1030")
	@RequestMapping("list")
	@ResponseBody
	public Map<String, Object> SelectForPage(ProgressQuery query) {
		
		Map<String, Object> result = progressService.selectForPage(query);
		
		return result;
		
	}
	
	
	/**
	 * 新增
	 * @param progress
	 * @param request
	 * @return
	 */
	@RequestMapping("add")
	@ResponseBody
	public ResultInfo insert(Progress progress,HttpServletRequest request) {
		
		progressService.insert(progress,request);
		return success(Constant.SUCCESS_MSG);
	}
	
	
	@RequestMapping("update")
	@ResponseBody
	public ResultInfo update(Progress progress) {
		progressService.update(progress);
		return success(Constant.SUCCESS_MSG);
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public ResultInfo deleteBatch(String ids) {
		progressService.delete(ids);
		return success(Constant.SUCCESS_MSG);
	}
}

