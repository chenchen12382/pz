package com.fh.service;

import com.fh.base.AssertUtil;
import com.fh.dao.FinanceDao;
import com.fh.dao.PriceClassDao;
import com.fh.dao.UserDao;
import com.fh.dto.FinanceQuery;
import com.fh.exception.ParamException;
import com.fh.model.Finance;
import com.fh.model.PriceClass;
import com.fh.model.User;
import com.fh.util.CookieUtil;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/15.
 */
@Service
public class FinanceService {

    @Autowired
    private FinanceDao financeDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private PriceClassDao priceClassDao;


    public Map<String,Object> selectForPage(FinanceQuery query) {
        PageList<Finance> finances = financeDao.selectForPage(query,query.buildPageBounds());
        Paginator paginator = finances.getPaginator();
        Map<String,Object> result = new HashMap<>();
        result.put("paginator",paginator);
        result.put("rows",finances);
        result.put("total",paginator.getTotalCount());
        return result;

    }


    /**
     * 各中心报表
     * @param query
     * @return
     */
    public Map<String,Object> selectCenterList(FinanceQuery query, HttpServletRequest request) {
        //判断是哪个中心
        String userName = CookieUtil.getCookieValue(request,"userName");
        //查询中心
        User user=userDao.findByUserName(userName);
        AssertUtil.notNull(user,"请关闭浏览器重试！");
        String center = user.getCenter();
        query.setUserCenter(center);
        //时间匹配
        Date start = query.getStart();
        Date over = query.getOver();
        if(start!=null||over!=null){
            if(start==null){
                throw new ParamException("请选择开始时间");
            }
            if(over==null){
                throw new ParamException("请选择结束时间");
            }
            //计算时间为传进来的时间+1天-1s
            long mm=over.getTime()+1000*60*60*24-1;
            over=new Date(mm);
            query.setOver(over);
        }


        PageList<Finance> finances = financeDao.selectCenterList(query,query.buildPageBounds());
        Paginator paginator = finances.getPaginator();
        Map<String,Object> result = new HashMap<>();
        result.put("paginator",paginator);
        result.put("rows",finances);
        result.put("total",paginator.getTotalCount());
        return result;

    }
    //财务字段添加
    public void insert(Finance finance, HttpServletRequest request) {
        //基本参数验证
        chickParams(finance);
        //课程判断
        //price,shouldMoney
//        String saleClass=finance.getSaleClass();
//        Integer saleNum = finance.getSaleNum();
//        Integer classHour = 0;

        //判断是哪个中心
        String userName = CookieUtil.getCookieValue(request,"userName");
        //查询中心
        User user=userDao.findByUserName(userName);
        AssertUtil.notNull(user,"系统出错，请联系管理员");
        String center = user.getCenter();
        finance.setCenter(center);

        buildFinance(finance);

        //亲子课
//        if(saleClass.equals("亲子课")){
//           if(saleNum<=48){
//               classHour = 48;
//               PriceClass priceClasses = priceClassDao.findByClassHour(classHour, saleClass);
//               //做计算
//               makeFinance(finance,priceClasses);
//
//           }
//           if(saleNum>48&&saleNum<=60){
//               classHour = 60;
//               PriceClass priceClasses = priceClassDao.findByClassHour(classHour, saleClass);
//               //做计算
//               makeFinance(finance,priceClasses);
//
//           }
//
//           if(saleNum>60&&saleNum<=96){
//               classHour = 96;
//               PriceClass priceClasses = priceClassDao.findByClassHour(classHour,saleClass);
//               //做计算
//               makeFinance(finance,priceClasses);
//
//           }
//
//           if(saleNum>96){
//               classHour = 192;
//               PriceClass priceClasses = priceClassDao.findByClassHour(classHour, saleClass);
//               //做计算
//               makeFinance(finance,priceClasses);
//           }
//
//
//        }
//
//        //乐博士
//        if(saleClass.contains("乐博士")){
//            //乐博士全天/半天/双语班
//
//                if(saleNum<3){
//                    classHour = 1;
//                    PriceClass priceClasses = priceClassDao.findByClassHour(classHour, saleClass);
//                    makeFinance(finance,priceClasses);
//                }
//                if(saleNum>=3&&saleNum<=6){
//                    classHour = 3;
//                    PriceClass priceClasses = priceClassDao.findByClassHour(classHour, saleClass);
//                    makeFinance(finance,priceClasses);
//                }
//
//                if(saleNum>6){
//                    classHour = 6;
//                    PriceClass priceClasses = priceClassDao.findByClassHour(classHour, saleClass);
//                    makeFinance(finance,priceClasses);
//                }
//
//            }
//
//            //幼小衔接
//        if(saleClass.equals("幼小衔接")){
//            //price,shouldMoney，discount
//            //标准单价  110/天
//
//            finance.setPrice(110);
//
//            //销售数量/节
//            int shouldMoney = 110*saleNum;
//            finance.setShouldMoney(shouldMoney);
//
//            //折扣
//            int realMoney  = finance.getRealMoney();
//            double temp = (double) realMoney /(double) shouldMoney*100;
//            String discount = (int)temp+"折";
//            finance.setDiscount(discount);
//
//            financeDao.insert(finance);
//
//        }
//        //启稚课程
//        if(saleClass.equals("启稚课程")){
//            if(saleNum<=3){
//                classHour=1;
//                PriceClass priceClasses = priceClassDao.findByClassHour(classHour, saleClass);
////                int price=priceClasses.getPrice();
//                makeFinance(finance,priceClasses);
//            }else if(saleNum>3&&saleNum<6){
//                classHour=3;
//                PriceClass priceClasses = priceClassDao.findByClassHour(classHour, saleClass);
//                makeFinance(finance,priceClasses);
//            }else{
//                classHour=6;
//                PriceClass priceClasses = priceClassDao.findByClassHour(classHour, saleClass);
//                makeFinance(finance,priceClasses);
//            }
//
//        }

        financeDao.insert(finance);

    }




    /**
     * 构建亲子课财务表字段
     * @param finance
     * @param priceClasses
     */
    private void makeFinance(Finance finance, PriceClass priceClasses) {
        //price,shouldMoney，discount
        //标准单价
        Integer price=priceClasses.getPrice();
        finance.setPrice(price);
        //销售数量/节
        int saleNum = finance.getSaleNum();
        int shouldMoney = price*saleNum;
        finance.setShouldMoney(shouldMoney);
        //折扣
        int realMoney  = finance.getRealMoney();
        double temp = (double) realMoney /(double) shouldMoney*100;
        String discount = (int)temp+"折";
        finance.setDiscount(discount);
//           return finance;
//        financeDao.insert(finance);
    }

    /**
     * 构建报表
     * @param finance
     */
    public void buildFinance(Finance finance){

        //课程判断
        //price,shouldMoney
        String saleClass=finance.getSaleClass();
        Integer saleNum = finance.getSaleNum();
        Integer classHour = 0;


        //亲子课
        if(saleClass.equals("亲子课")){
            if(saleNum<=48){
                classHour = 48;
                PriceClass priceClasses = priceClassDao.findByClassHour(classHour, saleClass);
                //做计算
                makeFinance(finance,priceClasses);

            }
            if(saleNum>48&&saleNum<=60){
                classHour = 60;
                PriceClass priceClasses = priceClassDao.findByClassHour(classHour, saleClass);
                //做计算
                makeFinance(finance,priceClasses);

            }

            if(saleNum>60&&saleNum<=96){
                classHour = 96;
                PriceClass priceClasses = priceClassDao.findByClassHour(classHour,saleClass);
                //做计算
                makeFinance(finance,priceClasses);

            }

            if(saleNum>96){
                classHour = 192;
                PriceClass priceClasses = priceClassDao.findByClassHour(classHour, saleClass);
                //做计算
                makeFinance(finance,priceClasses);
            }


        }

        //乐博士
        if(saleClass.contains("乐博士")){
            //乐博士全天/半天/双语班

            if(saleNum<3){
                classHour = 1;
                PriceClass priceClasses = priceClassDao.findByClassHour(classHour, saleClass);
                makeFinance(finance,priceClasses);
            }
            if(saleNum>=3&&saleNum<=6){
                classHour = 3;
                PriceClass priceClasses = priceClassDao.findByClassHour(classHour, saleClass);
                makeFinance(finance,priceClasses);
            }

            if(saleNum>6){
                classHour = 6;
                PriceClass priceClasses = priceClassDao.findByClassHour(classHour, saleClass);
                makeFinance(finance,priceClasses);
            }

        }

        //幼小衔接
        if(saleClass.equals("幼小衔接")){
            //price,shouldMoney，discount
            //标准单价  110/天

            finance.setPrice(110);

            //销售数量/节
            int shouldMoney = 110*saleNum;
            finance.setShouldMoney(shouldMoney);

            //折扣
            int realMoney  = finance.getRealMoney();
            double temp = (double) realMoney /(double) shouldMoney*100;
            String discount = (int)temp+"折";
            finance.setDiscount(discount);

            financeDao.insert(finance);

        }
        //启稚课程
        if(saleClass.equals("启稚课程")){
            if(saleNum<=3){
                classHour=1;
                PriceClass priceClasses = priceClassDao.findByClassHour(classHour, saleClass);
//                int price=priceClasses.getPrice();
                makeFinance(finance,priceClasses);
            }else if(saleNum>3&&saleNum<6){
                classHour=3;
                PriceClass priceClasses = priceClassDao.findByClassHour(classHour, saleClass);
                makeFinance(finance,priceClasses);
            }else{
                classHour=6;
                PriceClass priceClasses = priceClassDao.findByClassHour(classHour, saleClass);
                makeFinance(finance,priceClasses);
            }

        }


    }



    /**
     * 基本参数验证
     * @param finance
     */
    public static void chickParams(Finance finance){
        AssertUtil.isNotEmpty(finance.getXybh(),"请填写协议编号");
        AssertUtil.isNotEmpty(finance.getSjbh(),"请填写收据编号");
        AssertUtil.isNotEmpty(finance.getName(),"请填写客户姓名");
        AssertUtil.isNotEmpty(finance.getSaleClass(),"请选择课程");
        AssertUtil.intIsNotEmpty(finance.getSaleNum(),"请填写销售数量");
        AssertUtil.intIsNotEmpty(finance.getRealMoney(),"请填写实收金额");
        AssertUtil.isNotEmpty(finance.getPayMode(),"请填写支付方式");
        AssertUtil.isNotEmpty(finance.getCounselor(),"请填写销售顾问");
        AssertUtil.isNotEmpty(finance.getSource(),"请选择来源");

    }


    public void update(Finance finance) {
        chickParams(finance);
        Integer id = finance.getId();
        AssertUtil.intIsNotEmpty(id,"请选择记录进行删除");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date createDate=financeDao.findCreateDate(id);
        String dateFromDB=sdf.format(createDate);
        String now = sdf.format(new Date());
        if(!dateFromDB.equals(now)){
            throw new ParamException("您不能修改不是当天的记录");
        }

        buildFinance(finance);

        financeDao.update(finance);


    }


    public void deleteBatch(String ids) {
        AssertUtil.isNotEmpty(ids,"请选择记录");

        financeDao.deleteBatch(ids);
    }
}
