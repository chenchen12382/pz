package com.fh.controller;

import com.fh.annotation.RequirePermissions;
import com.fh.base.BaseController;
import com.fh.base.ResultInfo;
import com.fh.model.Role;
import com.fh.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/11.
 */
@Controller
@RequestMapping("role")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;


    @RequestMapping("index")
    public String index(){
        return "role";
    }

    @RequirePermissions(permission = "9020")
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> findList(){
        Map<String, Object> result = roleService.findAll();
        return result;

    }

    @RequestMapping("add")
    @ResponseBody
    public ResultInfo add(String roleName, String roleRemark) {
        roleService.add(roleName, roleRemark);
        return success("添加成功");
    }

    @RequestMapping("update")
    @ResponseBody
    public ResultInfo update(Role role){
        roleService.update(role);
        return success("修改成功");
    }

    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo delete(String ids){
        roleService.delete(ids);
        return success("删除成功");

    }

    @RequestMapping("find_all")
    @ResponseBody
    public List<Role> findAll(){
        Map<String,Object> result = roleService.findAll();
        return (List<Role>) result.get("rows");

    }


}
