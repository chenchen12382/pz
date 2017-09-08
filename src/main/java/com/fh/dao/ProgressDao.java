package com.fh.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import com.fh.dto.ProgressQuery;
import com.fh.model.Progress;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

public interface ProgressDao {

	PageList<Progress> selectForPage(ProgressQuery query, PageBounds buildPageBounds);
	
	@Insert("insert into tb_progressforms ( create_man, center,phone,hope_money,finish_money,unfinish_money,"
			+ " marks,is_valid,create_date,update_date ) values (#{createMan},#{center},"
			+ "  #{phone},#{hopeMoney},#{finishMoney},#{unfinishMoney},#{marks},1,now(),now())")
	void insert(Progress progress);
	
	@Update("update tb_progressforms set center=#{center},phone=#{phone},hope_money=#{hopeMoney}, "
			+ " finish_money=#{finishMoney},unfinish_money=#{unfinishMoney},marks=#{marks},update_date=now()"
			+ " where id=#{id} ")
	void update(Progress progress);

}
