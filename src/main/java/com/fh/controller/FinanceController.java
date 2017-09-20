package com.fh.controller;

import com.fh.base.BaseController;
import com.fh.base.ResultInfo;
import com.fh.dao.PriceClassDao;
import com.fh.dto.FinanceQuery;
import com.fh.model.District;
import com.fh.model.Finance;
import com.fh.model.User;
import com.fh.service.FinanceService;
import com.fh.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping("index_center")
    public String indexCenter(){
        return "day_report";
    }



    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> selectForPage(FinanceQuery query){
        Map<String,Object> result=financeService.selectForPage(query);
        return result;

    }


    @RequestMapping("center_list")
    @ResponseBody
    public Map<String,Object> selectCenterList(FinanceQuery query, HttpServletRequest request){
        Map<String,Object> result=financeService.selectCenterList(query,request);
        return result;

    }



    @RequestMapping("add")
    @ResponseBody
    public ResultInfo insert(Finance finance,HttpServletRequest request){
        financeService.insert(finance,request);
        return success("添加成功");

    }



    @RequestMapping("update")
    @ResponseBody
    public ResultInfo update(Finance finance){
        financeService.update(finance);
        return success("更新成功");

    }

    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo deleteBatch(String ids){
        financeService.deleteBatch(ids);
        return success("删除成功");

    }

}
