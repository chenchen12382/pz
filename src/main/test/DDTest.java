import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fh.model.DDToken;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/7.
 */
public class DDTest {


//    public String test() {
//
//        CloseableHttpClient httpclient = HttpClients.createDefault();
//        // 创建httppost
//        HttpGet get = new HttpGet("https://oapi.dingtalk.com/gettoken?corpid=ding99fa8e98ae730cdc&corpsecret=23NCa4twtF30gOGGq5ipvhFi3nKjC850yHJSC5Wj0j3PcjpeJoz4aQjpM_Ql-0tM");
//        String accessToken="";
//        try {
//            CloseableHttpResponse response = httpclient.execute(get);
//            try {
//                HttpEntity entity = response.getEntity();
//                if (entity != null) {
//                    Integer code=response.getStatusLine().getStatusCode();
//                    String result = EntityUtils.toString(entity, "UTF-8");
//                  DDToken  ddToken =  JSON.parseObject(result,DDToken.class);
//                    accessToken = ddToken.getAccessToken();
//                    System.out.println(accessToken);
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
//        return accessToken;
//    }
//
//
//    @Test
//    public void message(){
//        String test = test();
//
//        CloseableHttpClient httpclient = HttpClients.createDefault();
//        // 创建httppost
//        HttpPost post = new HttpPost("https://oapi.dingtalk.com/message/send?access_token="+test);
//        post.setHeader("Accept-Charset","utf-8");
//        post.setHeader("agentid","134093280");
//        Map<String,String> text = new HashMap<>();
//        text.put("content","擦擦12345c王企鹅");
////        List<NameValuePair> param = new ArrayList<NameValuePair>();
////        param.add(new BasicNameValuePair("msgtype","text"));
////        param.add(new BasicNameValuePair("text","{\"content\":\"陈琛的请假申请\"}"));
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("touser","13120007601222355");
//        jsonObject.put("agentid","134093280");
//        jsonObject.put("msgtype","text");
//        Object content = JSON.toJSON(text);
//        jsonObject.put("text",content);
//
//
////        DDToken ddToken = new DDToken();
//        UrlEncodedFormEntity uefEntity;
//        try {
//            StringEntity entity = new StringEntity(jsonObject.toJSONString(),"utf-8");
////            entity.setContentEncoding( "UTF-8" );
//            entity.setContentType( "application/json;charset=utf-8" );
//            post.setEntity(entity);
//            CloseableHttpResponse response = httpclient.execute(post);
//            try {
//                HttpEntity entitys = response.getEntity();
//                if (entity != null) {
//                    Integer code=response.getStatusLine().getStatusCode();
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



}
