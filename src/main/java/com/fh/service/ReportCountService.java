package com.fh.service;

import com.fh.dao.ReportCountDao;

import com.fh.dto.ReportCountQuery;

import com.fh.model.ReportCount;
import com.fh.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Administrator on 2017/10/9.
 */
@Service
public class ReportCountService {

    @Autowired
    private ReportCountDao reportCountDao;


    public Map<String,Object> selectForPage(ReportCountQuery query) {

        if(query.getStart()!=null){
            Date start=DateUtil.getFisrtDayOfMonth(query.getStart());
            query.setStart(start);
        }

        if(query.getOver()==null && query.getStart()!=null){
            query.setOver(new Date());
        }
        //查询区域业绩,完成率
        List<ReportCount> reportCounts = new ArrayList<>();
        List<String> district =  reportCountDao.selectAll();
        for ( int i = 0; i<district.size();i++){
            query.setDistrict(district.get(i));
            List<ReportCount> selectForDB = reportCountDao.selectForPage(query);
            if(null==selectForDB.get(0)){
                continue;
            }
            reportCounts.addAll(selectForDB);
        }
        //计算Footer统计
        List footer = new ArrayList<>();
        Map<String,Object> values = new HashMap<>();
        Integer income = 0;
        Integer target = 0;
        Integer discount = 0;
        int count = 0 ;
        for(int i = 0 ; i < reportCounts.size(); i++){
            if(reportCounts.get(i).getIncome()!=null) {
                income += reportCounts.get(i).getIncome();
            }
            if(reportCounts.get(i).getTarget()!=null) {
                target += reportCounts.get(i).getTarget();
            }
            if(reportCounts.get(i).getDiscount()!=null) {
                discount += reportCounts.get(i).getDiscount();
                count++;
            }

        }
        discount = discount/count;
        values.put("district","总计");
        values.put("income",income);
        values.put("target",target);
        values.put("discount",discount);
        footer.add(values);




        Map<String,Object> result = new HashMap<>();
        result.put("rows",reportCounts);
        result.put("footer",footer);
        return  result;

    }
}
