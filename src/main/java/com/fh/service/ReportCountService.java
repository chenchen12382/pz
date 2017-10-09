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
            Date start=DateUtil.getFisrtDayOfNow(query.getStart());
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
            if(selectForDB.get(0).getDistrict()==null){
                continue;
            }
            reportCounts.addAll(selectForDB);
        }


        Map<String,Object> result = new HashMap<>();
        result.put("rows",reportCounts);
        return  result;


    }
}
