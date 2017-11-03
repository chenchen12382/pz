package com.fh.service;

import com.fh.dao.CenterDao;
import com.fh.dao.DistrictDao;
import com.fh.dao.ReportCountDao;
import com.fh.dto.ReportCountQuery;
import com.fh.model.District;
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

    @Autowired
    private DistrictDao districtDao;

    @Autowired
    private CenterDao centerDao;

    public Map<String,Object> selectForPage(ReportCountQuery query) {

        buildQueryTime(query);
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

        //计算指标
//        if(query.getStart()!=null&&query.getOver()!=null){
            query.setOver(DateUtil.getLastDayOfDate(query.getOver()));

        //设置target的值
            for(int i=0;i<reportCounts.size();i++){
                query.setDistrict(reportCounts.get(i).getDistrict());
                Integer income = reportCounts.get(i).getIncome();
                Integer targets = reportCountDao.queryTargetByDistrict(query);
                reportCounts.get(i).setTarget(targets);
                //转换成浮点数
                if(targets == null){
                    reportCounts.get(i).setDiscount(0);
                    continue;
                }
                double a = income;
                double b = targets;
                double c = a/b*1000;
                Integer discount = (int) c ;
                reportCounts.get(i).setDiscount(discount);
            }

//        }


        //计算Footer统计
        List footer = new ArrayList<>();
        Map<String,Object> values = new HashMap<>();
        Integer income = 0;
        Integer target = 0;
        Integer discounts = 0;
        int count = 0 ;
        for(int i = 0 ; i < reportCounts.size(); i++){
            if(reportCounts.get(i).getIncome()!=null) {
                income += reportCounts.get(i).getIncome();
            }
            if(reportCounts.get(i).getTarget()!=null) {
                target += reportCounts.get(i).getTarget();
            }
            if(reportCounts.get(i).getDiscount()!=null) {
                discounts += reportCounts.get(i).getDiscount();
                count++;
            }

        }
        if(discounts !=0 ) {
            discounts = discounts / count;
        }
        values.put("district","总计");
        values.put("income",income);
        values.put("target",target);
        values.put("discount",discounts);
        footer.add(values);


        Map<String,Object> result = new HashMap<>();
        result.put("rows",reportCounts);
        result.put("footer",footer);
        return  result;

    }

    /**
     * 业绩分析
     * @param query
     * @return
     */
    public Map<String,Object> selectYjfx(ReportCountQuery query) {

        //查询区域信息
        List<String> district = districtDao.findAllDistrict();
        List total = new ArrayList();
//        if(query.getStart() == null){
//            query.setStart(DateUtil.getFirstDayOfDate(new Date()));
//            query.setOver(DateUtil.getLastDayOfDate(new Date()));
//        }
//
//        if(query.getStart() !=null && query.getOver()==null){
//            query.setOver(new Date());
//        }

        buildQueryTime(query);
        //查询区域总收入
        for (int i=0;i<district.size();i++) {
            String  d = district.get(i);
            query.setDistrict(d);
            Integer count = reportCountDao.findTotalByDistrict(query);
            if(count != null) {
                total.add(count);
            }else {
                total.add(0);
            }
        }

        Map<String,Object> result = new HashMap<>();
        result.put("district",district);
        result.put("total",total);
        return result;


    }

    public Map<String,Object> selectZxsrfx(ReportCountQuery query) {
        //查询中心信息
        List<String> center = centerDao.selectAllCenter();
        List total = new ArrayList();
        buildQueryTime(query);
        for (int i=0;i<center.size();i++){
            String  d = center.get(i);
            query.setCenter(d);
            Integer count = reportCountDao.findTotalByCenter(query);
            if(count != null) {
                total.add(count);
            }else {
                total.add(0);
            }
        }
        Map<String,Object> result = new HashMap<>();
        result.put("center",center);
        result.put("total",total);
        return result;


    }

    /**
     * 时间判断
     * @param query
     */
    public void buildQueryTime(ReportCountQuery query){
        if(query.getStart() == null){
            query.setStart(DateUtil.getFirstDayOfDate(new Date()));
            query.setOver(DateUtil.getLastDayOfDate(new Date()));
        }

        if(query.getStart() !=null && query.getOver()==null){
            query.setOver(new Date());
        }
    }



}
