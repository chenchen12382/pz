package com.fh.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.*;

import com.fh.dto.TargetQuery;
import com.fh.model.Target;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;


/**
 * Created by Administrator on 2017/10/9.
 */
public interface TargetDao {
	
	    PageList<Target> selectForPage(TargetQuery query, PageBounds buildPageBounds);
	
		@Select("select id,district,month,target,create_date,update_date from t_target where is_valid=1 ")
	    List<Target> selectAll();

	    @Insert("insert into t_target (district,month,target,is_valid,create_date,update_date )  values " +
	            " (#{district},#{month},#{target},1,now(),now())")
	    void insert(Target target);

	    @Update("update  t_target  set target=#{target},update_date=now() where id= #{id} ")
	    void update(Target target);

	    @Update("update t_target set is_valid = 0 , update_date=now()  where id in (${ids})")
	    void deleteBatch(@Param(value = "ids") String ids);


		@Select("select count(`month`) from t_target where district=#{district} and  is_valid= 1  and `month` like '${month}%' ")
		Integer selectForMonth(@Param(value = "district") String district, @Param(value = "month") String month );
}
