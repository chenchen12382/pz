package com.fh.dao;

import com.fh.dto.CenterTotalQuery;
import com.fh.model.CenterTotal;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Administrator on 2017/9/29.
 */
public interface CenterTotalDao {

    List<String> selectAllCenter();

    List<CenterTotal> selectForPage(CenterTotalQuery query);

    @Select("SELECT  t3.center from t_user t1 LEFT JOIN t_permission_center t2 on t1.id=t2.user_id " +
            " LEFT JOIN t_center t3 on t2.center_id=t3.id  where t1.user_name = #{userName}")
    List<String> findByPermissionCenter(@Param("userName") String userName);

    List<CenterTotal> findCenterTotalToday(CenterTotalQuery query, PageBounds pageBounds);


    List<CenterTotal> findCenterTotalToday(CenterTotalQuery query);
}
