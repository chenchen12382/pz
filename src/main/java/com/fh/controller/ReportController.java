package com.fh.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.fh.annotation.RequirePermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fh.base.BaseController;
import com.fh.base.Constant;
import com.fh.base.ResultInfo;
import com.fh.dto.ReportQuery;
import com.fh.model.Report;
import com.fh.service.ReportService;

@Controller
@RequestMapping("report")
public class ReportController extends BaseController {
	
	@Autowired
	private ReportService reportservice;
	
	@RequestMapping("index")
	public String index() {
		
		return "report";
	}
	
	@RequirePermissions(permission = "1020")
	@RequestMapping("list")
	@ResponseBody
	public Map<String, Object> List(ReportQuery query){
		Map<String, Object> result = reportservice.selectForPage(query);
		return result;
		
	}
	
	@RequestMapping(value = "add")
	@ResponseBody
	public ResultInfo insert(Report report,HttpServletRequest request) {
		reportservice.insert(report,request);
		return success(Constant.SUCCESS_MSG);
	}
	
	@RequestMapping("update")
	@ResponseBody
	public ResultInfo update(Report report) {

		reportservice.update(report);
		return success(Constant.SUCCESS_MSG);
		
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public ResultInfo delete(String ids) {
		reportservice.deleteBatch(ids);
		return success("删除成功");
		
	}
	
}
