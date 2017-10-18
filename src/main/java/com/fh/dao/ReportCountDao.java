package com.fh.dao;

import com.fh.dto.ReportCountQuery;
import com.fh.model.ReportCount;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Administrator on 2017/10/9.
 */
public interface ReportCountDao {


    List<String> selectAll();


    List<ReportCount> selectForPage(ReportCountQuery query);

    Integer queryTargetByDistrict(ReportCountQuery query);

    @Select("select sum(real_money) as total  from t_district t1 LEFT JOIN t_center t2 on t1.district = t2.district " +
            " LEFT JOIN t_finance t3 on t2.center = t3.center where t1.district = #{district} and t3.is_valid = 1 ")
    Integer findTotalByDistrict(@Param(value = "district") String district);

    @Select("select SUM(real_money) as count from t_finance where center = #{center} and is_valid = 1 ")
    Integer findTotalByCenter(@Param(value = "center") String center);
}
