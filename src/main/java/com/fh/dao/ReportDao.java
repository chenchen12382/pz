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
	
	
	@Insert("insert into tb_reportforms (name,phone,center,subscribe_people,arrive_people, "
			+ " order_people,new_order,old_order,one_day_money,hope_money,marks,is_valid,create_date,update_date) "
			+ " values (#{name},#{phone},#{center},#{subscribePeople},#{arrivePeople}, "
			+ " #{orderPeople},#{newOrder},#{oldOrder},#{oneDayMoney},#{hopeMoney},#{marks},1,now(),now()) ")
	void insert(Report report);

	
	@Update("update tb_reportforms set phone=#{phone}, center=#{center},subscribe_people=#{subscribePeople}, "
			+ "  arrive_people=#{arrivePeople},order_people=#{orderPeople},new_order=#{newOrder},"
			+ " old_order=#{oldOrder},one_day_money=#{oneDayMoney},hope_money=#{hopeMoney},marks=#{marks},update_date=now() where id = #{id} ")
	void update(Report report);

	@Update("update tb_reportforms set is_valid=0,update_date=now() where id in (${ids})")
	void deleteBatch(@Param("ids")String ids);
	
}
