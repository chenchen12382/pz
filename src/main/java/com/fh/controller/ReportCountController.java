package com.fh.controller;

import com.fh.base.BaseController;
import com.fh.dto.ReportCountQuery;
import com.fh.service.ReportCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @RequestMapping("index")
    public String index(){
        return "report_count";
    }


    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> selectForPage(ReportCountQuery query){

        Map<String,Object> result=reportCountService.selectForPage(query);
        return result;

    }



}
