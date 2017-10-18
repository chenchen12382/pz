package com.fh.dao;

import com.fh.dto.FinanceQuery;
import com.fh.model.Finance;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;

/**
 * Created by Administrator on 2017/9/15.
 */
public interface FinanceDao {


    PageList<Finance> selectForPage(FinanceQuery query, PageBounds pageBounds);

    PageList<Finance> selectCenterList(FinanceQuery query, PageBounds pageBounds);

    void insert(Finance finance);

    void update(Finance finance);

    @Update("update t_finance set is_valid=0,update_date=now() where id in (${ids})")
    void deleteBatch(@Param(value = "ids") String ids);

    @Select("select create_date from t_finance where id=#{id}")
    Date findCreateDate(@Param(value = "id") Integer id);

    Integer findShouldCount(FinanceQuery query);

    Integer findRealCount(FinanceQuery query);

    Integer findDiscount(FinanceQuery query);

    Integer findCount(FinanceQuery query);

    @Insert("INSERT INTO t_finance SET name=#{name},center=#{center},xybh =#{xybh},sjbh=#{sjbh},hybh=#{hybh},is_valid = 1,create_date=now(),update_date=now()")
    void addAgreement(Finance finance);

    @Update("update t_finance set xybh =#{xybh},sjbh=#{sjbh},hybh=#{hybh} where id = #{id}")
    void updateAgreement(Finance finance);
}
