package com.fh.controller;
import com.fh.base.BaseController;
import com.fh.dto.AnalyzeTotalQuery;
import com.fh.service.AnalyzeTotalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/29.
 */
@Controller
@RequestMapping("analyzeTotal")
public class AnalyzeTotalController extends BaseController {

    @Autowired
    private AnalyzeTotalService analyzeTotalService;

    @RequestMapping("index")
    public String index(){
    	
        return "adviserAnalyze";
    }
  
    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> selectForPage(AnalyzeTotalQuery query, HttpServletRequest request){
        Map<String,Object> result=analyzeTotalService.selectForPage(query);
        return result;

    }

}
