package com.fh.controller;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fh.annotation.RequirePermissions;
import com.fh.base.BaseController;
import com.fh.base.ResultInfo;
import com.fh.dto.TargetQuery;
import com.fh.model.Target;
import com.fh.service.TargetService;


/**
 * Created by Administrator on 2017/10/9.
 */
@Controller
@RequestMapping("target")
public class TargetController extends BaseController {

	private static final String[] Request = null;
	@Autowired
	private TargetService targetService;

	@RequestMapping("index")
	public String index() {
		return "target_list";
	}

	@RequirePermissions(permission = "3020")
	@RequestMapping("list")
	@ResponseBody
	public Map<String, Object> List(TargetQuery query){
		Map<String, Object> result = targetService.selectForPage(query);
		return result;
		
	}
	@RequestMapping("add")
	@ResponseBody
	public ResultInfo insert(Target target) {
		targetService.insert(target);	
		return success("添加成功");
	}
	
	@RequestMapping("update")
	@ResponseBody
	public ResultInfo update(Target target) {
		targetService.update(target);
		return success("修改成功");
	}

	@RequestMapping("delete")
	@ResponseBody
	public ResultInfo deleteBatch(String ids) {
		targetService.deleteBatch(ids);
		return success("删除成功");
	}

}
