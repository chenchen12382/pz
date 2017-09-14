package com.fh.dao;

import com.fh.model.PriceClass;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by Administrator on 2017/9/14.
 */
public interface PriceClassDao {


    @Select("select id,sale_class,price,create_date,update_date from t_class_price where is_valid = 1 ")
    List<PriceClass> findAll();

    @Insert("insert into t_class_price (sale_class,price,is_valid,create_date,update_date) values " +
            " ( #{saleClass},#{price},1,now(),now()) ")
    void insert(PriceClass priceClass);

    @Update("update t_class_price set sale_class=#{saleClass},price=#{price},update_date=now() where id=#{id}")
    void update(PriceClass priceClass);

    @Update("update t_class_price set is_valid = 0,update_date = now()  where id in (${ids})")
    void deleteBatch(@Param(value = "ids") String ids) ;
}
