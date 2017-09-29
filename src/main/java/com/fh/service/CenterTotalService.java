package com.fh.service;

import com.fh.dao.CenterTotalDao;
import com.fh.dto.CenterTotalQuery;
import com.fh.model.CenterTotal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/29.
 */
@Service
public class CenterTotalService {

    @Autowired
    private CenterTotalDao centerTotalDao;


    public Map<String,Object> selectForPage(CenterTotalQuery query) {
        //查询区域 中心
        List<String> centers = centerTotalDao.selectAllCenter();
        List<CenterTotal> centerTotals =new ArrayList<>();
        for ( int i = 0; i<centers.size();i++){
            String center = centers.get(i);
            List<CenterTotal> selectForDB = centerTotalDao.selectForPage(center,query);
            centerTotals.addAll(selectForDB);
        }
//        List<CenterTotal> centerTotals = centerTotalDao.selectForPage(center,query);
        Map<String,Object> result = new HashMap<>();
        result.put("rows",centerTotals);
        return  result;

    }
}
