package com.fh.service;

import com.fh.base.BaseQuery;
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

        if(time!=null) {
//            time = 1;

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
        String role="";
        if(!userName.equals("dd_message")) {
            role = userDao.findUserRole(userName);
        }else {
            role="";
        }
        if (role.equals("中心管理员")) {
            String center = userDao.findUserCenter(userName);
            centers.add(center);
        } else if (role.equals("区域总监")||role.equals("投资人")){
            centers = centerTotalDao.findByPermissionCenter(userName);
        }else {
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

    public String findCenterTotalToday() {

        CenterTotalQuery query = new CenterTotalQuery();
          query.setTime(0);
          String userName = "dd_message";

          List<CenterTotal> centerTotals = selectForMobilePage(query,userName);

          String result = "各中心收入统计 \n";
          for (int i=0;i<centerTotals.size();i++){
              if(i==centerTotals.size()-1){
                  result+=centerTotals.get(i).getCenter()+"--总订单:"+centerTotals.get(i).getOrderTotal()+"总收入:"+centerTotals.get(i).getRealTotal()+"\n";
              }else {
                  result+="中心:"+centerTotals.get(i).getCenter()+" 订单:"+centerTotals.get(i).getOrderTotal()+" 收入:"+centerTotals.get(i).getRealTotal()+"\n";
              }

          }
          return  result;
    }
}
