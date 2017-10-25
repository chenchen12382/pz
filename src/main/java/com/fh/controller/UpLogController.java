package com.fh.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fh.annotation.RequirePermissions;
import com.fh.base.BaseController;
import com.fh.base.BaseQuery;
import com.fh.base.ResultInfo;
import com.fh.model.Center;
import com.fh.model.UpLog;
import com.fh.service.CenterService;
import com.fh.service.UpLogService;

/**
 * Created by Administrator on 2017/10/25.
 */
@Controller
@RequestMapping("up_log")
public class UpLogController extends BaseController {
	
	@Autowired
	private UpLogService upLogService;

	@RequestMapping("index")
	public String index() {
		return "up_log";
	}

	@RequestMapping("list")
	@ResponseBody
	public Map<String, Object> selectForPage(BaseQuery query) {
		Map<String, Object> result = upLogService.selectForPage(query);
		return result;
	}

	@RequestMapping("find_all")
	@ResponseBody
	public List<UpLog> findAll() {
		Map<String, Object> result = upLogService.selectAll();
		return (List<UpLog>) result.get("rows");

	}

	@RequestMapping("add")
	@ResponseBody
	public ResultInfo insert(UpLog upLog) {
		upLogService.insert(upLog);
		return success("添加成功");
	}

	@RequestMapping("update")
	@ResponseBody
	public ResultInfo update(UpLog upLog) {
		upLogService.update(upLog);
		return success("修改成功");
	}

	@RequestMapping("delete")
	@ResponseBody
	public ResultInfo deleteBatch(String ids) {
		upLogService.deleteBatch(ids);
		return success("删除成功");
	}

}
