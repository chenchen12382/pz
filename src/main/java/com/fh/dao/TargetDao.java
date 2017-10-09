package com.fh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.fh.model.Target;


/**
 * Created by Administrator on 2017/10/9.
 */
public interface TargetDao {
	
	   @Select("select id,district,month,target,create_date,update_date from t_target where is_valid=1 ")
	    List<Target> selectAll();

	    @Insert("insert into t_target (district,month,target,is_valid,create_date,update_date )  values " +
	            " (#{district},#{month},#{target},1,now(),now())")
	    void insert(Target target);

	    @Update("update  t_target  set district=#{district},month=#{month},target=#{target},update_date=now() where id= #{id} ")
	    void update(Target target);

	    @Update("update t_target set is_valid = 0 , update_date=now()  where id in (${ids})")
	    void deleteBatch(@Param("ids") String ids);


}