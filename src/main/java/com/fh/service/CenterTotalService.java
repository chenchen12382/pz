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

        //时间判断
        Date start =query.getStart();
        Date over = query.getOver();
        if(start==null){

//            Calendar calendar=Calendar.getInstance();
//            Date theDate=calendar.getTime();
//            GregorianCalendar gcLast=(GregorianCalendar)Calendar.getInstance();
//            gcLast.setTime(theDate);
//            //设置为第一天
//            gcLast.set(Calendar.DAY_OF_MONTH, 1);
//            Date day_first=gcLast.getTime();
//            query.setStart(day_first);
//            DateUtil.getFisrtDayOfNow(start);
            query.setStart(DateUtil.getFisrtDayOfNow(start));

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
//        List<CenterTotal> centerTotals = centerTotalDao.selectForPage(center,query);
        Map<String,Object> result = new HashMap<>();
        result.put("rows",centerTotals);
        return  result;

    }


}
