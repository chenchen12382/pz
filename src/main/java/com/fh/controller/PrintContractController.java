package com.fh.controller;

import com.fh.base.BaseController;
import com.fh.base.BaseQuery;
import com.fh.base.ResultInfo;
import com.fh.dto.FinanceQuery;
import com.fh.model.Finance;
import com.fh.model.PrintContract;
import com.fh.service.PrintContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/20.
 */

@Controller
@RequestMapping("print_contract")
public class PrintContractController extends BaseController {

    @Resource
    private PrintContractService printContractService;

    @RequestMapping("index")
    public String printContract(){
        return "print_contract";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> selectForPage(BaseQuery query){
        Map<String,Object> result=printContractService.selectForPage(query);
        return result;

    }


    @RequestMapping("add")
    @ResponseBody
    public ResultInfo insert(PrintContract printContract){
        printContractService.insert(printContract);
        return success("添加成功");

    }


}
