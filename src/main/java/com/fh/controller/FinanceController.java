package com.fh.controller;

import com.fh.annotation.RequirePermissions;
import com.fh.base.BaseController;
import com.fh.base.ResultInfo;
import com.fh.dto.FinanceQuery;
import com.fh.model.Finance;
import com.fh.service.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
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

    @RequirePermissions(permission="1010")
    @RequestMapping("index_center")
    public String indexCenter(){
        return "day_report";
    }


    @RequirePermissions(permission = "2010")
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> selectForPage(FinanceQuery query){
        Map<String,Object> result=financeService.selectForPage(query);
        return result;

    }

    @RequirePermissions(permission = "1010")
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
    public ResultInfo deleteBatch(String ids,HttpServletRequest request){
        financeService.deleteBatch(ids,request);
        return success("删除成功");
    }

    //作废单据
    @RequestMapping("add_agreement")
    @ResponseBody
    public ResultInfo addAgreement(HttpServletRequest request,Finance finance){
        financeService.addAgreement(request,finance);
        return success("添加成功");

    }

    //作废单据修改
    @RequestMapping("update_agreement")
    @ResponseBody
    public ResultInfo updateAgreement(Finance finance){
        financeService.updateAgreement(finance);
        return success("修改成功");

    }

    //导出excel
    @RequestMapping("excel")
    @ResponseBody
    public void exportExcel(FinanceQuery query,HttpServletResponse response){
        financeService.exportExcel(query,response);
//        return success("导出成功");

    }

    //上传图片
    @RequestMapping("uploadImg")
    @ResponseBody
    public ResultInfo uploadImg(@RequestParam(value = "uploadImg", required = false) MultipartFile file, HttpServletRequest request){

        String result = financeService.uploadImg(file,request);


        return success(result);
    }

}
