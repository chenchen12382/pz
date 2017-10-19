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


    Integer findTotalByDistrict(ReportCountQuery query);

    @Select("select SUM(real_money) as count from t_finance where center = #{center} and is_valid = 1 ")
    Integer findTotalByCenter(@Param(value = "center") String center);
}
