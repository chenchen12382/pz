package com.fh.controller;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fh.base.BaseQuery;
import com.fh.dto.CenterTotalQuery;
import com.fh.model.CenterTotal;
import com.fh.service.AnalyzeTotalService;
import com.fh.service.CenterTotalService;
import com.fh.util.DateUtil;
import com.fh.util.HttpUtil;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fh.base.BaseController;
import com.fh.service.UserService;
import com.fh.util.LoginUserUtil;
import com.fh.vo.UserLoginIdentity;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController extends BaseController {


    @Autowired
    private UserService userService;

    @Autowired
    private CenterTotalService centerTotalService;
    
    @Autowired
    private AnalyzeTotalService analyzeTotalService;
    
    @RequestMapping("index")
    public String index() {

        return "index";
    }

    @RequestMapping("main")
    public String main(Model model, HttpServletRequest request) {
        // 获取登录用户的信息
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        UserLoginIdentity userLoginIdentity = userService.findLoginUser(userId);
        model.addAttribute("currentUser", userLoginIdentity);
        return "main";
    }

    @RequestMapping("mobile_main")
    public String mobileMain(Model model, HttpServletRequest request) {
        // 获取登录用户的信息
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        UserLoginIdentity userLoginIdentity = userService.findLoginUser(userId);
        model.addAttribute("currentUser", userLoginIdentity);
        return "mobile_main";
    }


    /**
     * 无效代码
     *
     * @param name
     * @param password
     * @return
     */
    @RequestMapping("test")
    @ResponseBody
    public Map<String, Object> clintTest(String name, String password) {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> date = new HashMap<>();
        List list = new ArrayList();
        date.put("parkId", 1);
        date.put("parkName", "B1停车场");
        date.put("totalNum", 100);
        date.put("freeSpaceNum", 80);
        list.add(date);
        result.put("resCode", 1);
        result.put("resMag", "");
        result.put("data", list);
        return result;

    }

//   @RequestMapping("dd_test")
//    public void message() {
//        String test = HttpUtil.gettoken();
//
//        CloseableHttpClient httpclient = HttpClients.createDefault();
//        // 创建httppost
//        HttpPost post = new HttpPost("https://oapi.dingtalk.com/message/send?access_token=" + test);
//        post.setHeader("Accept-Charset", "utf-8");
//        post.setHeader("agentid", "134093280");
//        //获得当天的统计数据
//       CenterTotalQuery query = new CenterTotalQuery();
//        String centerTotals = centerTotalService.findCenterTotalToday(query);
//        Map<String, String> text = new HashMap<>();
//        text.put("content", centerTotals);
////        List<NameValuePair> param = new ArrayList<NameValuePair>();
////        param.add(new BasicNameValuePair("msgtype","text"));
////        param.add(new BasicNameValuePair("text","{\"content\":\"陈琛的请假申请\"}"));
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("touser", "13120007601222355");
//        jsonObject.put("agentid", "134093280");
//        jsonObject.put("msgtype", "text");
//        Object content = JSON.toJSON(text);
//        jsonObject.put("text", content);
//
//
////        DDToken ddToken = new DDToken();
//        UrlEncodedFormEntity uefEntity;
//        try {
//            StringEntity entity = new StringEntity(jsonObject.toJSONString(), "utf-8");
////            entity.setContentEncoding( "UTF-8" );
//            entity.setContentType("application/json;charset=utf-8");
//            post.setEntity(entity);
//            CloseableHttpResponse response = httpclient.execute(post);
//            try {
//                HttpEntity entitys = response.getEntity();
//                if (entity != null) {
//                    Integer code = response.getStatusLine().getStatusCode();
//                    String result = EntityUtils.toString(entitys, "UTF-8");
//                    System.out.println(result);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            } finally {
//                response.close();
//            }
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
//        } catch (UnsupportedEncodingException e1) {
//            e1.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            // 关闭连接,释放资源
//            try {
//                httpclient.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//
//    }
    @RequestMapping("test1")
    @ResponseBody
    public String test1(){
    	String result=analyzeTotalService.findAnalyzeTotalToday(null);
    	return result;
    	
    	
    }

}
