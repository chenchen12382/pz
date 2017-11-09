package com.fh.controller;

import com.fh.base.BaseController;
import com.fh.service.ProtocolNumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/11/9.
 */
@Controller
@RequestMapping("protocol")
public class ProtocolNumController extends BaseController{

//    @Autowired
//    private ProtocolNumService protocolNumService;


    @RequestMapping("index/{type}")
    public String index(@PathVariable Integer type){
        switch (type){
            default:
                return "xybh";
            case 2 :
                return "sjbh";
        }
    }

}
