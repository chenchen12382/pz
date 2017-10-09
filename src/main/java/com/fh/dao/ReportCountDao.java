package com.fh.dao;

import com.fh.dto.ReportCountQuery;
import com.fh.model.ReportCount;

import java.util.List;

/**
 * Created by Administrator on 2017/10/9.
 */
public interface ReportCountDao {


    List<String> selectAll();


    List<ReportCount> selectForPage(ReportCountQuery query);
}
