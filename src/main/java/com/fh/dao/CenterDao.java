package com.fh.dao;

import com.fh.dto.ReportCountQuery;
import com.fh.model.Center;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by Administrator on 2017/9/14.
 */
public interface CenterDao {


    @Select("select id,center,district,create_date,update_date from t_center where is_valid=1 order by district  ")
    PageList<Center> selectAll(PageBounds pageBounds);

    @Insert("insert into t_center (center,district,is_valid,create_date,update_date )  values " +
            " (#{center},#{district},1,now(),now())")
    void insert(Center center);

    @Update("update t_center set center=#{center},district=#{district},update_date=now() where id= #{id} ")
    void update(Center center);

    @Update("update t_center set is_valid = 0 , update_date=now()  where id in (${ids})")
    void deleteBatch(@Param("ids") String ids);

    @Select("select center from t_center where center = #{center} and is_valid = 1 ")
    String findByCenter(@Param("center") String center);

    @Select("select id,center,district,create_date,update_date from t_center where is_valid=1 order by district  ")
    List<Center> selectCenter();

    @Select("select center from t_center where is_valid = 1")
    List<String> selectAllCenter();
}
