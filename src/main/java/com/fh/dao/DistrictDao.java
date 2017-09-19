package com.fh.dao;

import com.fh.model.District;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by Administrator on 2017/9/18.
 */
public interface DistrictDao {

    @Select("select id,district,remark,create_date,update_date from t_district where is_valid = 1" +
            " order by id ")
    PageList<District> selectForPage(PageBounds pageBounds) ;

    @Insert("insert into t_district (district,remark,is_valid,create_date,update_date) values " +
            " ( #{district},#{remark},1,now(),now() )")
    void insert(District district);

    @Update("update t_district set district=#{district},remark = #{remark},update_date=now() where" +
            " id=#{id} ")
    void update(District district);

    @Update("update t_district set is_valid = 0 where id in (${ids})")
    void deleteBatch(@Param(value = "ids") String ids);

    @Select("select id,district,remark,create_date,update_date from t_district where is_valid = 1" +
            " order by id ")
    List<District> findAll();
}
