package com.fh.dao;

import com.fh.model.Center;
import com.fh.model.PriceClass;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Administrator on 2017/9/14.
 */
public interface PriceClassDao {


    @Select("select  id,sale_class,price,class_hour,site_price,create_date,update_date from t_class_price where is_valid = 1 ")
    List<PriceClass> findAll();

    @Insert("insert into t_class_price (sale_class,price,class_hour,site_price,is_valid,create_date,update_date) values " +
            " ( #{saleClass},#{price},#{classHour},#{sitePrice},1,now(),now()) ")
    void insert(PriceClass priceClass);

    @Update("update t_class_price set sale_class=#{saleClass},price=#{price},class_hour=#{classHour}," +
            " site_price=#{sitePrice},update_date=now() where id=#{id}")
    void update(PriceClass priceClass);

    @Delete("DELETE FROM t_class_price where id in (${ids}) ")
    void deleteBatch(@Param(value = "ids") String ids) ;

    @Select("select id,sale_class,price,class_hour,site_price,create_date,update_date from t_class_price where is_valid = 1 " +
            " and class_hour = #{classHour} and sale_class = #{saleClass}")
    PriceClass findByClassHour(@Param(value = "classHour") Integer classHour, @Param(value = "saleClass") String saleClass);

    @Select("select distinct sale_class from t_class_price where is_valid = 1 ")
    List<PriceClass> findSaleClass();
}
