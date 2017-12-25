package com.fh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.fh.dto.AnalyzeTotalQuery;
import com.fh.dto.CenterTotalQuery;
import com.fh.model.AnalyzeTotal;
import com.fh.model.CenterTotal;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;


public interface AnalyzeTotalDao {
	 List<String> selectAllCenter();
	 List<AnalyzeTotal> selectForPage(AnalyzeTotalQuery query);
	 
	/* @Select("SELECT  t3.center from t_user t1 LEFT JOIN t_permission_center t2 on t1.id=t2.user_id " +
	            " LEFT JOIN t_center t3 on t2.center_id=t3.id  where t1.user_name = #{userName}")
	List<String> findByPermissionCenter(@Param("userName") String userName);*/
	 List<AnalyzeTotal> findAnalyzeTotalToday(AnalyzeTotalQuery query, PageBounds pageBounds);
    List<AnalyzeTotal> findAnalyzeTotalToday(AnalyzeTotalQuery query);

}
