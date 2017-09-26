package com.fh.controller;

import com.fh.annotation.RequirePermissions;
import com.fh.base.BaseController;
import com.fh.base.BaseQuery;
import com.fh.base.ResultInfo;
import com.fh.model.Module;
import com.fh.model.Permission;
import com.fh.service.ModuleService;
import com.fh.service.PermissionService;
import com.fh.vo.ModuleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collections;
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

    @Autowired
    private PermissionService permissionService;


    @RequestMapping("index")
    public String index(){
        return "module";
    }

    @RequirePermissions(permission = "9030")
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

    @RequestMapping("update")
    @ResponseBody
    public ResultInfo update(Module module){
        moduleService.update(module);
        return success("修改成功");
    }

    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo delete(String ids) {
        moduleService.deleteBatch(ids);
        return success("删除成功");
    }


    @RequestMapping("relate_permission")
    public String relatePermission(Integer roleId, Model model) {
        List<ModuleVO> moduleVOs = moduleService.findAllModules(roleId);
        model.addAttribute("modules", moduleVOs);
        model.addAttribute("roleId", roleId);
        return "role_module";
    }

    @RequestMapping("dorelate")
    @ResponseBody
    public ResultInfo doRelate(Integer roleId, Integer moduleId, boolean checked) {
        permissionService.addDoRelate(roleId, moduleId, checked);
        return success("操作成功");
    }


}
