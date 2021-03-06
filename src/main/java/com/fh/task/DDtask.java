package com.fh.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fh.service.AnalyzeTotalService;
import com.fh.service.CenterTotalService;
import com.fh.util.HttpUtil;
import com.fh.util.TaskUtil;
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

    @Autowired
    private AnalyzeTotalService analyzeTotalService;

    @Scheduled(cron="0 0 19  * * ? ")
    public void dayReportMessage() {
        String centerTotals = centerTotalService.findCenterTotalToday();
        TaskUtil.task(centerTotals,"13120007601222355|0262681145785834|046021380423352691|103927254723352752|603769051177168|10392725401054131");
    }


    @Scheduled(cron="0 39 16  * * ? ")
    public void gwMessage() {
        String analyzeTotals = analyzeTotalService.findAnalyzeTotalToday();
        TaskUtil.task(analyzeTotals,"065009136424245699");
    }


}
