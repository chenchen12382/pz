package com.fh.controller;

import com.fh.annotation.RequirePermissions;
import com.fh.base.BaseController;
import com.fh.base.ResultInfo;
import com.fh.model.Center;
import com.fh.model.PriceClass;
import com.fh.service.PriceClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/14.
 */

@Controller
@RequestMapping("priceClass")
public class PriceClassController extends BaseController{

    @Autowired
    private PriceClassService priceClassService;

    @RequestMapping("index")
    public String index(){
        return "price_class";
    }

    @RequirePermissions(permission = "2020")
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> findList(){
        Map<String,Object> result = priceClassService.findAll();
        return result;

    }

    @RequestMapping("find_all")
    @ResponseBody
    public List<PriceClass> findAll() {
        List<PriceClass> result = priceClassService.findSaleClass();

        return result;
    }

    @RequestMapping("add")
    @ResponseBody
    public ResultInfo insert(PriceClass priceClass){

        priceClassService.insert(priceClass);
        return success("添加成功");
    }

    @RequestMapping("update")
    @ResponseBody
    public ResultInfo update(PriceClass priceClass){
        priceClassService.update(priceClass);
        return success("更新成功");
    }

    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo deleteBatch(String ids){
        priceClassService.deleteBatch(ids);
        return success("删除成功");
    }
}
