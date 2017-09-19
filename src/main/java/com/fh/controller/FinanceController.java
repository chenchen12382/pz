package com.fh.controller;

import com.fh.base.BaseController;
import com.fh.base.ResultInfo;
import com.fh.dto.FinanceQuery;
import com.fh.model.District;
import com.fh.service.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by Administrator on 2017/9/15.
 */


@Controller
@RequestMapping("finance")
public class FinanceController extends BaseController{

    @Autowired
    private FinanceService financeService;

    @RequestMapping("index")
    public String index(){
        return "finance";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> selectForPage(FinanceQuery query){
        Map<String,Object> result=financeService.selectForPage(query);
        return result;

    }




}
