package com.fh.controller;

import com.fh.base.BaseController;
import com.fh.base.BaseQuery;
import com.fh.base.ResultInfo;
import com.fh.dto.CenterTotalQuery;
import com.fh.dto.ProtocolNumQuery;
import com.fh.model.ProtocolNum;
import com.fh.service.ProtocolNumService;

import java.util.List;
import java.util.Map;

import com.fh.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/11/9.
 */
@Controller
@RequestMapping("protocol")
public class ProtocolNumController extends BaseController {

    @Autowired
    private ProtocolNumService protocolNumService;

    @RequestMapping("index/{type}")
    public String index(@PathVariable Integer type) {
        switch (type) {
            default:
                return "xybh";
            case 2:
                return "sjbh";
            case 3:
                return "xybh_lbs";
        }
    }

    /*收据编号*/
    @RequestMapping("listSjbh")
    @ResponseBody
    public Map<String, Object> selectForPage1(ProtocolNumQuery query) {

        Map<String, Object> result = protocolNumService.selectForPage1(query);
        return result;
    }

    @RequestMapping("deleteSjbh")
    @ResponseBody
    public ResultInfo deleteBatchSjbh(String ids) {
        protocolNumService.deleteBatchSjbh(ids);
        return success("删除成功");
    }

    /*协议编号*/
    @RequestMapping("listXybh")
    @ResponseBody
    public Map<String, Object> selectForPage2(ProtocolNumQuery query) {
        Map<String, Object> result = protocolNumService.selectForPage2(query);
        return result;
    }

    @RequestMapping("deleteXybh")
    @ResponseBody
    public ResultInfo deleteBatch2(String ids) {
        protocolNumService.deleteBatchXybh(ids);
        return success("删除成功");
    }

    @RequestMapping("list_xybh_lbs")
    @ResponseBody
    public Map<String,Object> selectXybhLbs(ProtocolNumQuery query){
        Map<String,Object> result = protocolNumService.selectXybhLbs(query);
        return result;
    }



    @RequestMapping("find_all")
    @ResponseBody
    public List<ProtocolNum> findAll(HttpServletRequest request,Integer type) {
        String userName = CookieUtil.getCookieValue(request, "userName");

        List<ProtocolNum> result = protocolNumService.findAll(userName,type);
        return result;
    }

}
