package com.fh.controller;

import com.fh.annotation.RequirePermissions;
import com.fh.base.BaseController;
import com.fh.dto.ReportCountQuery;
import com.fh.service.ReportCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by Administrator on 2017/10/9.
 */
@Controller
@RequestMapping("report_count")
public class ReportCountController extends BaseController{


    @Autowired
    private ReportCountService reportCountService;


    @RequestMapping("index/{type}")
    public String index(@PathVariable Integer type){
        switch (type){
            default:
                return "report_count";
            case 2 :
                return "yjfx";
        }

    }


    @RequirePermissions(permission = "3030")
    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> selectForPage(ReportCountQuery query){

        Map<String,Object> result=reportCountService.selectForPage(query);
        return result;

    }

    @RequirePermissions(permission = "3040")
    @RequestMapping("yjfx")
    @ResponseBody
    public Map<String, Object> selectYjfx(ReportCountQuery query){
        Map<String,Object> result = reportCountService.selectYjfx(query);
        return result;
    }


}
