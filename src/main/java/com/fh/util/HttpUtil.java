package com.fh.util;

import com.alibaba.fastjson.JSON;
import com.fh.model.DDToken;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2017/12/8.
 */
public class HttpUtil {

    /**
     * 获得token
     * @return
     */
    public static String gettoken() {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建httppost

        //
        HttpGet get = new HttpGet("https://oapi.dingtalk.com/gettoken?corpid= 你的钉钉corpid ");
        String accessToken="";
        try {
            CloseableHttpResponse response = httpclient.execute(get);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    Integer code=response.getStatusLine().getStatusCode();
                    String result = EntityUtils.toString(entity, "UTF-8");
                    DDToken ddToken =  JSON.parseObject(result,DDToken.class);
                    accessToken = ddToken.getAccessToken();
                    System.out.println(accessToken);
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
        return accessToken;
    }
}
