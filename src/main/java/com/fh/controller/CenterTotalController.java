package com.fh.controller;

import com.fh.annotation.RequirePermissions;
import com.fh.base.BaseController;
import com.fh.dto.CenterTotalQuery;
import com.fh.model.CenterTotal;
import com.fh.service.CenterTotalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/29.
 */
@Controller
@RequestMapping("centerTotal")
public class CenterTotalController extends BaseController {

    @Autowired
    private CenterTotalService centerTotalService;

    @RequestMapping("index")
    public String index(){
        return "center_total";
    }

    @RequirePermissions(permission = "3010")
    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> selectForPage(CenterTotalQuery query){

        Map<String,Object> result=centerTotalService.selectForPage(query);
        return result;

    }


}
