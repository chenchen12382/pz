package com.fh.controller;

import com.fh.annotation.RequirePermissions;
import com.fh.base.BaseController;
import com.fh.base.BaseQuery;
import com.fh.base.ResultInfo;
import com.fh.dao.CenterDao;
import com.fh.dao.CenterTotalDao;
import com.fh.model.Center;
import com.fh.model.ProtocolNum;
import com.fh.service.CenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/14.
 */
@Controller
@RequestMapping("center")
public class CenterController extends BaseController {

    @Autowired
    private CenterService centerService;



    @RequestMapping("index")
    public String index(){
        return "center_list";

    }

    @RequirePermissions(permission = "9040")
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> selectForPage(BaseQuery query){
        Map<String,Object> result = centerService.selectForPage(query);
        return result;
    }

    @RequestMapping("find_all")
    @ResponseBody
    public List<Center> findAll() {
        Map<String, Object> result = centerService.selectAll();
        return (List<Center>) result.get("rows");

    }

    @RequestMapping("add")
    @ResponseBody
    public ResultInfo insert(Center center){
        centerService.insert(center);
        return success("添加成功");
    }

    @RequestMapping("update")
    @ResponseBody
    public ResultInfo update(Center center){
        centerService.update(center);
        return success("修改成功");
    }

    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo deleteBatch(String ids){
        centerService.deleteBatch(ids);
        return success("删除成功");
    }

    @RequestMapping("readExcel")
    @ResponseBody
    public ResultInfo readExcel(@RequestParam(value = "uploadExl", required = false) MultipartFile file, ProtocolNum protocolNum){
        centerService.readExcel(file,protocolNum);
        return success("导入成功");
    }


}
