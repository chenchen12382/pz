package com.fh.service;

import com.fh.dao.CenterTotalDao;
import com.fh.dao.UserDao;
import com.fh.dto.CenterTotalQuery;
import com.fh.model.CenterTotal;
import com.fh.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Administrator on 2017/9/29.
 */
@Service
public class CenterTotalService {

    @Autowired
    private CenterTotalDao centerTotalDao;

    @Autowired
    private UserDao userDao;


    public Map<String, Object> selectForPage(CenterTotalQuery query, String userName) {

        List<CenterTotal> centerTotal = bulidList(query, userName);
//        List<CenterTotal> centerTotals = centerTotalDao.selectForPage(center,query);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", centerTotal);
        return result;

    }

    /**
     * 构建查询讯息
     *
     * @param query
     * @param userName
     * @return
     */
    public List bulidList(CenterTotalQuery query, String userName) {
        //时间判断
        Integer time=query.getTime();
        if(query.getStart()!=null&&query.getOver()!=null){
            DateUtil.getMinTimeOfDay(query.getStart());
            DateUtil.getMaxTimeOfDay(query.getOver());
        }
        if(query.getStart()!=null&&query.getOver()==null){
            query.setOver(new Date());
        }
        if (query.getStart()==null){
            query.setStart(DateUtil.getFisrtDayOfMonth(new Date()));
            query.setOver(new Date());
        }

        if(time==null&&query.getStart()==null) {
            time = 1;

            if (time == 0) {
                //当天数据
                query.setStart(DateUtil.getMinTimeOfDay(new Date()));
            } else {
                query.setStart(DateUtil.getFirstDayOfDate(new Date()));
            }
            query.setOver(new Date());
        }



        //查询区域 中心
        List<String> centers = new ArrayList<>();
        String role = userDao.findUserRole(userName);
        if (role.equals("中心管理员")) {
            String center = userDao.findUserCenter(userName);
            centers.add(center);
        } else {
            centers = centerTotalDao.selectAllCenter();
        }
        List<CenterTotal> centerTotals = new ArrayList<>();
        for (int i = 0; i < centers.size(); i++) {
            query.setCenter(centers.get(i));
            List<CenterTotal> selectForDB = centerTotalDao.selectForPage(query);
            if (selectForDB.get(0).getCenter() == null) {
                continue;
            }
            centerTotals.addAll(selectForDB);
        }
        return centerTotals;
    }

    public List<CenterTotal> selectForMobilePage(CenterTotalQuery query, String userName) {
        List<CenterTotal> result = bulidList(query, userName);
        CenterTotal centerTotal = new CenterTotal();
        Integer orderTotals = 0;
        Integer realTotals = 0;
        for (int i = 0; i < result.size(); i++) {
            orderTotals += result.get(i).getOrderTotal(); //订单合计
            realTotals += result.get(i).getRealTotal(); //总实收
        }
        centerTotal.setCenter("合计");
        centerTotal.setOrderTotal(orderTotals);
        centerTotal.setRealTotal(realTotals);
        result.add(centerTotal);
        return result;
    }
}
