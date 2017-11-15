package com.fh.service;

import com.fh.dao.CenterTotalDao;
import com.fh.dto.CenterTotalQuery;
import com.fh.model.CenterTotal;
import com.fh.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2017/9/29.
 */
@Service
public class CenterTotalService {

    @Autowired
    private CenterTotalDao centerTotalDao;


    public Map<String,Object> selectForPage(CenterTotalQuery query) {

        List<CenterTotal> centerTotal= bulidList(query);
//        List<CenterTotal> centerTotals = centerTotalDao.selectForPage(center,query);
        Map<String,Object> result = new HashMap<>();
        result.put("rows",centerTotal);
        return  result;

    }

    public  List bulidList(CenterTotalQuery query){
        //时间判断
        Date start =query.getStart();
        Date over = query.getOver();
        if(start==null){

            query.setStart(DateUtil.getFirstDayOfDate(new Date()));

        }
        if(over==null) {
            query.setOver(new Date());
        }
        //查询区域 中心
        List<String> centers = centerTotalDao.selectAllCenter();
        List<CenterTotal> centerTotals =new ArrayList<>();
        for ( int i = 0; i<centers.size();i++){
            query.setCenter(centers.get(i));
            List<CenterTotal> selectForDB = centerTotalDao.selectForPage(query);
            if(selectForDB.get(0).getCenter()==null){
                continue;
            }
            centerTotals.addAll(selectForDB);
        }
        return  centerTotals ;
    }

    public List<CenterTotal> selectForMobilePage(CenterTotalQuery query) {
        List<CenterTotal> result=bulidList(query);
        return result;
    }
}
