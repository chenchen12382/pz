package com.fh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/10/25.
 */
@Controller
@RequestMapping("up_log")
public class UpLogController {

    @RequestMapping("index")
    public String index(){
        return "up_log";
    }

}
