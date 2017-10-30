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
			+ " in_num,source,order_num,money,analysis) "
			+ " values (#{name},#{phone_num},#{center},#{plan_num},#{arrive_num}, "
			+ " #{in_num},#{source},#{order_num},#{money},#{analysis},1,now(),now()) ")
	void insert(Report report);

	
	@Update("update tb_reportforms set name=#{name} phone_num=#{phone_num}, center=#{center},plan_num=#{plan_num}, "
			+ "  arrive_num=#{arrive_num},in_num=#{in_num},source=#{source},"
			+ " order_num=#{order_num},money=#{money},analysis=#{analysis},update_date=now() where id = #{id} ")
	void update(Report report);

	@Update("update tb_reportforms set is_valid=0,update_date=now() where id in (${ids})")
	void deleteBatch(@Param("ids")String ids);
	
}
