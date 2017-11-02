package com.fh.dao;

import com.fh.base.BaseQuery;
import com.fh.model.Module;
import com.fh.model.TreeMenu;
import com.fh.vo.ModuleVO;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
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

    void update(Module moduleFromDb);

    void deleteBatch(@Param(value = "ids") String ids);

    @Select("select id, module_name, parent_id from t_module where is_valid=1 order by parent_id, orders")
    @ResultType(value=ModuleVO.class)
    List<ModuleVO> findAll();

    List<Module> findByIds(@Param(value="ids")String ids);

    @Select("select id, module_name, parent_id, opt_value "
            + " from t_module where is_valid=1 and tree_path LIKE '${treePath}%'")
    List<Module> findSunModules(@Param(value = "treePath") String treePath);

    @Select("select id,parent_id as pId,module_name as name ,module_style as icon,url as page from t_module where is_valid=1 and opt_value in (${permission}) ")
    List<TreeMenu> findTreeMenu(@Param("permission") String permission);
}
