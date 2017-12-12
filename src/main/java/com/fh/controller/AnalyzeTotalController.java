package com.fh.controller;
import com.fh.base.BaseController;
import com.fh.dto.AnalyzeTotalQuery;
import com.fh.service.AnalyzeTotalService;
import com.fh.util.CookieUtil;
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
        String userName = CookieUtil.getCookieValue(request,"userName");
        Map<String,Object> result=analyzeTotalService.selectForPage(query,userName);
        return result;

    }


 /* @RequestMapping("mobile_index")
    public String mIndex(CenterTotalQuery query,Model model,HttpServletRequest request ){
        String userName = CookieUtil.getCookieValue(request,"userName");
        List<CenterTotal> result=analyzeTotalService.selectForMobilePage(query,userName);
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
*/

}
