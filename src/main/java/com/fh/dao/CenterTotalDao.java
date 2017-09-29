package com.fh.dao;

import com.fh.dto.CenterTotalQuery;
import com.fh.model.CenterTotal;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/9/29.
 */
public interface CenterTotalDao {

    List<String> selectAllCenter();

    List<CenterTotal> selectForPage(@Param("center") String center, CenterTotalQuery query);
}
