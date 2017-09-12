package com.fh.controller;

import com.fh.base.BaseController;
import com.fh.base.BaseQuery;
import com.fh.base.ResultInfo;
import com.fh.model.Module;
import com.fh.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/12.
 */

@Controller
@RequestMapping("module")
public class ModuleController extends BaseController{

    @Autowired
    private ModuleService moduleService;


    @RequestMapping("index")
    public String index(){
        return "module";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> selectForPage(BaseQuery query){

        Map<String,Object> result = moduleService.selectForPage(query);

        return result;

    }

    @RequestMapping("find_by_grade")
    @ResponseBody
    public List<Module> findByGrade(Integer grade) {
        List<Module> result = moduleService.findByGrade(grade);
        return result;
    }

    @RequestMapping("add")
    @ResponseBody
    public ResultInfo add(Module module) {
        moduleService.add(module);
        return success("添加成功");
    }


}
