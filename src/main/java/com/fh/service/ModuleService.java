package com.fh.service;

import com.fh.base.AssertUtil;
import com.fh.base.BaseQuery;
import com.fh.constant.ModuleGrade;
import com.fh.dao.ModuleDao;
import com.fh.exception.ParamException;
import com.fh.model.Module;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import freemarker.ext.beans.HashAdapter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/12.
 */
@Service
public class ModuleService {

    @Autowired
    private ModuleDao moduleDao;

    public Map<String,Object> selectForPage(BaseQuery query) {
        PageList<Module> modules = moduleDao.selectForPage(query.buildPageBounds());
        Map<String,Object> result = new HashMap<>();
        result.put("rows",modules);
        result.put("total",modules.getPaginator().getTotalCount());
        return  result;

    }

    public List<Module> findByGrade(Integer grade) {

        AssertUtil.notNull(grade, "请选择层级");
        return moduleDao.findByGrade(grade);

    }

    public void add(Module module) {
        // 基本参数验证
        checkParams(module);

        // 同级下模块名称唯一验证
        if  (module.getGrade() != ModuleGrade.ROOT.getGrade()) {
            checkModuleNameIsUnique(module);
        }
        String optValue = module.getOptValue();
        // 权限值唯一验证
        Module moduleFromDb = moduleDao.findByOptValue(optValue);
        AssertUtil.isTrue(moduleFromDb != null, "该权限值已存在");
        // 构建一个tree_path=,1,
        String treePath = null;
        if (module.getGrade() != ModuleGrade.ROOT.getGrade()) {
            treePath = buildTreePath(module);
        }
        module.setTreePath(treePath);
        // 保存
        moduleDao.insert(module);
    }


    /**
     * 基本參數验证
     * @param module
     */
    private static void checkParams(Module module) {
        // 基本参数验证
        String moduleName = module.getModuleName();
        AssertUtil.isNotEmpty(moduleName, "请输入模块名称");
        Integer orders = module.getOrders();
        AssertUtil.notNull(orders, "请输入排序值");
        String optValue = module.getOptValue();
        AssertUtil.isNotEmpty(optValue, "请输入权限值");
        if (module.getGrade() != ModuleGrade.ROOT.getGrade()
                && module.getParentId() == null) {
            throw new ParamException("请选择父级菜单");
        }

    }
    /**
     * 模块名唯一验证
     * @param module
     */
    private void checkModuleNameIsUnique(Module module) {
        Module moduleFromDb = moduleDao.findByModuleName(module.getModuleName().trim(), module.getParentId());
        AssertUtil.isTrue(moduleFromDb != null, "模块名已存在");
    }

    /**
     * 构建treepath
     * @param module
     * @return
     */
    private String buildTreePath(Module module) {
        Module parentModule = findById(module.getParentId());
        String parentTreePath = parentModule.getTreePath();
        String treePath = module.getTreePath();
        if (StringUtils.isBlank(parentTreePath)) {
            treePath = "," + module.getParentId() + ",";
        } else {
            treePath = parentTreePath + module.getParentId() + ",";
        }
        return treePath;
    }


    /**
     * 根据ID获取对象
     * @param moduleId
     * @return
     */
    public Module findById(Integer moduleId) {
        AssertUtil.intIsNotEmpty(moduleId, "请选择模块");
        Module module = moduleDao.findById(moduleId);
        AssertUtil.notNull(module, "该模块不存在");
        return module;
    }


}
