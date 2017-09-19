package com.fh.controller;

import com.fh.base.BaseController;
import com.fh.base.BaseQuery;
import com.fh.base.ResultInfo;
import com.fh.model.District;
import com.fh.model.Role;
import com.fh.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 区域管理
 * Created by Administrator on 2017/9/18.
 */
@Controller
@RequestMapping("district")
public class DistrictController extends BaseController{

    @Autowired
    private DistrictService districtService;

    @RequestMapping("index")
    public String index(){
        return "district";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> selectForPage(BaseQuery query){

        Map<String,Object> result=districtService.selectForPage(query);
        return result;
    }

    @RequestMapping("add")
    @ResponseBody
    public ResultInfo insert(District district){
        districtService.insert(district);
        return success("添加成功");

    }

    @RequestMapping("update")
    @ResponseBody
    public ResultInfo update(District district){
        districtService.update(district);
        return success("修改成功");
    }

    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo deleteBatch(String ids){
        districtService.deleteBatch(ids);
        return success("删除成功");
    }

    @RequestMapping("find_all")
    @ResponseBody
    public List<District> findAll(){
        List<District> result = districtService.findAll();
        return result;

    }



}
