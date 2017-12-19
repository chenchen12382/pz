package com.fh.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fh.service.CenterTotalService;
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
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/11.
 */
@Component
public class DDtask {

    @Autowired
    private CenterTotalService centerTotalService;

    @Scheduled(cron="0 0 19  * * ? ")
    public void message() {
        String test = HttpUtil.gettoken();

        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建httppost
        HttpPost post = new HttpPost("https://oapi.dingtalk.com/message/send?access_token=" + test);
        post.setHeader("Accept-Charset", "utf-8");
        post.setHeader("agentid", "134093280");
        //获得当天的统计数据
//        CenterTotalQuery query = new CenterTotalQuery();
        String centerTotals = centerTotalService.findCenterTotalToday();
        Map<String, String> text = new HashMap<>();
        text.put("content", centerTotals);
//        List<NameValuePair> param = new ArrayList<NameValuePair>();
//        param.add(new BasicNameValuePair("msgtype","text"));
//        param.add(new BasicNameValuePair("text","{\"content\":\"陈琛的请假申请\"}"));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("touser", "13120007601222355|046021380423352691|103927254723352752|0262681145785834");
        jsonObject.put("agentid", "134093280");
        jsonObject.put("msgtype", "text");
        Object content = JSON.toJSON(text);
        jsonObject.put("text", content);


//        DDToken ddToken = new DDToken();
        UrlEncodedFormEntity uefEntity;
        try {
            StringEntity entity = new StringEntity(jsonObject.toJSONString(), "utf-8");
//            entity.setContentEncoding( "UTF-8" );
            entity.setContentType("application/json;charset=utf-8");
            post.setEntity(entity);
            CloseableHttpResponse response = httpclient.execute(post);
            try {
                HttpEntity entitys = response.getEntity();
                if (entity != null) {
                    Integer code = response.getStatusLine().getStatusCode();
                    String result = EntityUtils.toString(entitys, "UTF-8");
                    System.out.println(result);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


}
