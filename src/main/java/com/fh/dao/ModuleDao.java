package com.fh.dao;

import com.fh.base.BaseQuery;
import com.fh.model.Module;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Administrator on 2017/9/12.
 */
public interface ModuleDao {


    PageList<Module> selectForPage(PageBounds pageBounds);

    @Select("select id, module_name from t_module where "
            + "is_valid=1 and grade = #{grade} order by orders")
    List<Module> findByGrade(@Param(value = "grade") Integer grade);

    Module findByModuleName(@Param(value = "moduleName") String moduleName, @Param(value = "parentId") Integer parentId);

    Module findByOptValue(@Param(value = "optValue") String optValue);

    Module findById(@Param(value = "id") Integer id);

    void insert(Module module);
}
