package com.fh.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.fh.dto.ReportQuery;
import com.fh.model.Report;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

public interface ReportDao {

	PageList<Report> selectForPage(ReportQuery query, PageBounds buildPageBounds);
	
	
	@Insert("insert into tb_reportforms (name,phone_num,center,plan_num,arrive_num, "
			+ " in_num,source,order_num,money,analysis,is_valid,create_date,update_date) "
			+ " values (#{name},#{phoneNum},#{center},#{planNum},#{arriveNum}, "
			+ " #{inNum},#{source},#{orderNum},#{money},#{analysis},1,now(),now()) ")
	void insert(Report report);
	
	
	@Update("update tb_reportforms set name=#{name}, phone_num=#{phoneNum},center=#{center},plan_num=#{planNum}, "
			+ " arrive_num=#{arriveNum},in_num=#{inNum},source=#{source},"
			+ " order_num=#{orderNum},money=#{money},analysis=#{analysis},update_date=now() where id = #{id} ")
	void update(Report report);
	
	
	@Update("update tb_reportforms set is_valid=0,update_date=now() where id in (${ids})")
	void deleteBatch(@Param("ids")String ids);
	
}
