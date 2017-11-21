package com.fh.controller;

import com.fh.annotation.RequirePermissions;
import com.fh.base.BaseController;
import com.fh.dto.CenterTotalQuery;
import com.fh.model.CenterTotal;
import com.fh.service.CenterTotalService;
import com.fh.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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
    public Map<String, Object> selectForPage(CenterTotalQuery query, HttpServletRequest request){
        String userName = CookieUtil.getCookieValue(request,"userName");
        Map<String,Object> result=centerTotalService.selectForPage(query,userName);
        return result;

    }


    @RequestMapping("mobile_index")
    public String mIndex(CenterTotalQuery query,Model model,HttpServletRequest request ){
        String userName = CookieUtil.getCookieValue(request,"userName");
        List<CenterTotal> result=centerTotalService.selectForMobilePage(query,userName);
        model.addAttribute("centerTotals",result);

       return "center_total_m";
    }

    @RequestMapping("m_list")
    @ResponseBody
    public List<CenterTotal> mList(CenterTotalQuery query,Model model,HttpServletRequest request ){
        String userName = CookieUtil.getCookieValue(request,"userName");
        List<CenterTotal> result=centerTotalService.selectForMobilePage(query,userName);
//        model.addAttribute("centerTotals",result);
        return result;
//        return "center_total_m";
    }


}
