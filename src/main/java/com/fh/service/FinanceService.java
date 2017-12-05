package com.fh.service;

import com.fh.base.AssertUtil;
import com.fh.dao.CenterDao;
import com.fh.dao.FinanceDao;
import com.fh.dao.PriceClassDao;
import com.fh.dao.UserDao;
import com.fh.dto.FinanceQuery;
import com.fh.exception.ParamException;
import com.fh.model.Finance;
import com.fh.model.PriceClass;
import com.fh.model.User;
import com.fh.util.CookieUtil;
import com.fh.util.DateUtil;
import com.fh.util.ExcelUtil;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

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

    @Autowired
    private CenterDao centerDao;


    public Map<String,Object> selectForPage(FinanceQuery query) {
        //格式化时间
        formartTime(query);
        PageList<Finance> finances = financeDao.selectForPage(query,query.buildPageBounds());
        Paginator paginator = finances.getPaginator();
        Map<String,Object> result = new HashMap<>();
        //计算Footer统计
        List footer = new ArrayList();
        Map<String,Object> keys = new HashMap<>();
        keys.put("center","总单量");
        keys.put("name","总应收金额");
        keys.put("xybh","总实收金额");
        keys.put("sjbh","综合折扣率");
        Map<String,Object> values = new HashMap<>();
        Integer centerCount = financeDao.findCount(query);
        values.put("center",centerCount);
        Integer shouldCount = financeDao.findShouldCount(query);
        values.put("name",shouldCount);
        Integer realCount = financeDao.findRealCount(query);
        values.put("xybh",realCount);
        //计算综合折扣率
        Integer discount = financeDao.findDiscount(query);
//        Integer counts = 0;
//        for ( int i = 0 ; i<discount.size();i++){
//            Integer temp = discount.get(i);
//           String[] temp = str.split("%");
//             counts += temp;
//        }
//        Integer count =  counts/discount.size();


        values.put("sjbh",discount+"%");
        footer.add(keys);
        footer.add(values);

        result.put("paginator",paginator);
        result.put("rows",finances);
        result.put("total",paginator.getTotalCount());
        result.put("footer",footer);
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
        //管理员
        String userRole = userDao.findUserRole(userName);

        //查询中心
        User user=userDao.findByUserName(userName);
        AssertUtil.notNull(user,"请关闭浏览器重试！");
        String center = user.getCenter();
        query.setUserCenter(center);
        //时间匹配
        formartTime(query);


        if(userRole.equals("系统管理员")){
            PageList<Finance> finances = financeDao.selectForPage(query,query.buildPageBounds());
            Paginator paginator = finances.getPaginator();
            Map<String,Object> result = new HashMap<>();
            result.put("paginator",paginator);
            result.put("rows",finances);
            result.put("total",paginator.getTotalCount());
            return result;

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
        if(finance.getProperty().equals("全款")||finance.getProperty().equals("订金")) {

            Integer countXybh = financeDao.queryFinanceXybh(finance.getXybh());
//            AssertUtil.intIsNotEmpty(countXybh,"该协议编号已存在，请检查");
            if(countXybh>0){
                throw new ParamException("该协议编号已存在，请检查!");
            }

            Integer countSjbh = financeDao.queryFinanceSjbh(finance.getSjbh());
//            AssertUtil.intIsNotEmpty(countSjbh,"该收据编号已存在，请检查");
            if(countSjbh>0){
                throw new ParamException("该收据编号已存在，请检查!");
            }



        }

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
        //构建日报表信息
        buildFinance(finance);


        //添加
        financeDao.insert(finance);

        //设置协议编号收据编号为不可用
//        centerDao.deleteSjbh(finance.getSjbh());
//        String xybh=finance.getXybh();
//        if(xybh.contains("PZ")){
//            centerDao.deleteXybh(xybh);
//        }else if(xybh.contains("PL")){
//            centerDao.deleteXybhLbs(xybh);
//        }

        deleteXYSJ(finance);



    }

    public void formartTime(FinanceQuery query){

        //时间匹配

        if(query.getStart()!=null && query.getOver()==null){
           query.setOver(new Date());
        }
        if (query.getStart()!=null && query.getOver()!=null){
            query.setOver(DateUtil.getMaxTimeOfDay(query.getOver()));
        }

        if(query.getStart()==null){
            query.setStart(DateUtil.getMinTimeOfDay(new Date()));
            query.setOver(new Date());

        }

//        Date start = query.getStart();
//        Date over = query.getOver();
//        if(start!=null||over!=null) {
//            if (start == null) {
//                throw new ParamException("请选择开始时间");
//            }
//            if (over == null) {
//                throw new ParamException("请选择结束时间");
//            }
//            //计算时间为传进来的时间+1天-1s
//            long mm = over.getTime() + 1000 * 60 * 60 * 24 - 1;
//            over = new Date(mm);
//            query.setOver(over);
//        }
    }


    /**
     * 构建亲子课财务表字段
     * @param finance
     * @param priceClasses
     */
    public void makeFinance(Finance finance, PriceClass priceClasses) {
        //price,shouldMoney，discount
        //标准单价
        Integer price=priceClasses.getPrice();
        finance.setPrice(price);
        //销售数量/节
        int saleNum = finance.getSaleNum();
        int shouldMoney = price*saleNum;


        int realMoney  = finance.getRealMoney();
        if(realMoney>shouldMoney){
            shouldMoney=realMoney;
        }
        finance.setShouldMoney(shouldMoney);
        //折扣
        double discount = (double) realMoney /(double) shouldMoney*100;
//        if((int)temp>100){
//            throw new ParamException("实收金额大于标准单价！");
//        }
//       if((int)temp<10){
//            throw new ParamException("输入的应收金额过低, 请检查课时或金额!");
//        }
//        String discount = (int)temp+"%";
        finance.setDiscount((int)discount);
        if(finance.getProperty().equals("订金")){
            //设置应收金额为实收金额
            finance.setShouldMoney(finance.getRealMoney());
            finance.setDiscount(100);

        }



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

            if(saleClass.contains("乐博士晚托班A")){

                if(saleClass.contains("乐博士晚托班A一小时")){
                classHour = 1;
                PriceClass priceClasses = priceClassDao.findByClassHour(classHour, saleClass);
                makeFinance(finance,priceClasses);
                }else{
                    classHour = 2;
                    PriceClass priceClasses = priceClassDao.findByClassHour(classHour, saleClass);
                    makeFinance(finance,priceClasses);
                }
            } else if (saleClass.contains("乐博士晚托班B")){
                classHour = 1;
                PriceClass priceClasses = priceClassDao.findByClassHour(classHour, saleClass);
                makeFinance(finance,priceClasses);
            }else{

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

//            financeDao.insert(finance);

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
//            int realMoney  = finance.getRealMoney();
            if(realMoney>shouldMoney){
                shouldMoney=realMoney;
            }
            finance.setShouldMoney(shouldMoney);
            double temp = (double) realMoney /(double) shouldMoney*100;
//            if(temp>100){
//                temp = 100;
//            }
//            if((int)temp>=100){
//                throw new ParamException("实收金额大于标准单价！");
//            }
//            if((int)temp<10){
//                throw new ParamException("输入的应收金额过低, 请检查课时或金额 ! ");
//            }
            Integer discount = (int)temp;
            finance.setDiscount(discount);

            if(finance.getProperty().equals("订金")){
                //设置应收金额为实收金额
                finance.setShouldMoney(finance.getRealMoney());
                finance.setDiscount(100);

            }

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

        if(saleClass.equals("英语游戏一个月")){
            classHour=1;
            PriceClass priceClasses = priceClassDao.findByClassHour(classHour, saleClass);
            makeFinance(finance,priceClasses);
        }

        if(saleClass.equals("英语游戏三个月")){
            classHour=3;
            PriceClass priceClasses = priceClassDao.findByClassHour(classHour, saleClass);
            makeFinance(finance,priceClasses);
        }
        
    }



    /**
     * 基本参数验证
     * @param finance
     */
    public void chickParams(Finance finance){
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


    public void deleteBatch(String ids, HttpServletRequest request) {
        AssertUtil.isNotEmpty(ids,"请选择记录");

        //判断是哪个中心
        String userName = CookieUtil.getCookieValue(request,"userName");
        //管理员
        String userRole = userDao.findUserRole(userName);

        if(userRole.equals("系统管理员")) {

            financeDao.deleteBatch(ids);
            return;
        }

        if(ids.contains(",")){
                throw new ParamException("只能选择一条数据删除！");
        }
        Integer id = Integer.parseInt(ids);

        AssertUtil.intIsNotEmpty(id,"请选择记录进行删除");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date createDate=financeDao.findCreateDate(id);
        String dateFromDB=sdf.format(createDate);
        String now = sdf.format(new Date());
        if(!dateFromDB.equals(now)){
            throw new ParamException("您不能修改不是当天的记录");
        }
        financeDao.deleteBatch(ids);
    }


    public void addAgreement(HttpServletRequest request, Finance finance) {
        //判断是哪个中心
        String userName = CookieUtil.getCookieValue(request,"userName");
        //查询中心
        User user=userDao.findByUserName(userName);
        AssertUtil.notNull(user,"系统出错，请联系管理员");
        String center = user.getCenter();
        finance.setName("作废单据");
        finance.setCenter(center);
        financeDao.addAgreement(finance);
        deleteXYSJ(finance);
    }

    public void updateAgreement(Finance finance) {
        Integer id = finance.getId();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date createDate=financeDao.findCreateDate(id);
        String dateFromDB=sdf.format(createDate);
        String now = sdf.format(new Date());
        if(!dateFromDB.equals(now)){
            throw new ParamException("您不能修改不是当天的记录");
        }
        financeDao.updateAgreement(finance);

    }

    /**
     * 导出excel
     * @param response
     * @param query
     * @param s
     */
    public void exportExcel(FinanceQuery query, HttpServletResponse response) {

        //获取数去
        String title = "营收日报表";
        ExcelUtil<Finance> ex = new ExcelUtil<>();
        String[] headers =
                {"协议编号", "中心", "收据编号", "会员编号", "会员姓名","销售课程","销售数量","协议签订类型","价格","应收金额","实际金额","折扣","顾问0、教师/1","支付方式",
                        "付款性质","签约时间","银行卡号","顾问姓名","促销/备注","赠送课程","来源",};
        formartTime(query);

        List<Finance> finances = financeDao.selectForExcel(query);
        OutputStream out = null;
        try {
            out = response.getOutputStream();
            String time=System.currentTimeMillis()+"";

            String fileName=new String("pz"+ time);
            response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xls");
            ex.exportExcel(headers,finances,out);
            out.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    public String uploadImg(MultipartFile file, HttpServletRequest request) {
        String userName=CookieUtil.getCookieValue(request,"userName");


        String center = userDao.findUserCenter(userName);

        String path = "d:/upload/"+center;

        System.out.println(path);
        String time=System.currentTimeMillis()+"";

        String fileName=new String("pz"+ time+".jpg");
//        String fileName = file.getOriginalFilename();
        File targetFile = new File(path,fileName);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        //保存
        try {
            file.transferTo(targetFile);
        }catch(Exception e){
            e.printStackTrace();
        }
        String result = targetFile.getPath();

        return result;
    }

    /**
     * 报表审核
     * @return
     */
    public Map<String,Object> selectExamine() {
        List<Finance> finances = financeDao.findExamineList();
        Map<String,Object> result = new HashMap<>();
        result.put("rows",finances);
        return result;

    }

    public void examineInsert(Integer state, Integer id) {
        AssertUtil.intIsNotEmpty(id,"请选择要审核的内容");
        AssertUtil.intIsNotEmpty(state,"请填写审核意见");
        financeDao.examineInsert(state,id);
    }

    /**
     * 设置收据协议编号为不可用
     *
     * @param finance
     */
    public void deleteXYSJ(Finance finance){
        //设置协议编号收据编号为不可用
        centerDao.deleteSjbh(finance.getSjbh());
        String xybh=finance.getXybh();
        if(xybh!="") {
            if (xybh.contains("PZ")) {
                centerDao.deleteXybh(xybh);
            }
            if (xybh.contains("PL")) {
                centerDao.deleteXybhLbs(xybh);
            }
        }

    }

}
