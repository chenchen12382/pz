package com.fh.dao;

import com.fh.model.Center;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by Administrator on 2017/9/14.
 */
public interface CenterDao {


    @Select("select id,center,district,create_date,update_date from t_center where is_valid=1 ")
    List<Center> selectAll();

    @Insert("insert into t_center (center,district,is_valid,create_date,update_date )  values " +
            " (#{center},#{district},1,now(),now())")
    void insert(Center center);

    @Update("update t_center set center=#{center},district=#{district},update_date=now() where id= #{id} ")
    void update(Center center);

    @Update("update t_center set is_valid = 0 , update_date=now()  where id in (${ids})")
    void deleteBatch(@Param("ids") String ids);
}
