package com.fh.controller;

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
public class RoleController {

    @Autowired
    private RoleService roleService;


    @RequestMapping("find_all")
    @ResponseBody
    public List<Role> findAll(){
        Map<String,Object> result = roleService.findAll();
        return (List<Role>) result.get("rows");



    }


}
