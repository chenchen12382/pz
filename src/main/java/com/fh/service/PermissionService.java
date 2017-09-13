package com.fh.service;

import com.fh.base.AssertUtil;
import com.fh.constant.ModuleGrade;
import com.fh.dao.ModuleDao;
import com.fh.dao.PermissionDao;
import com.fh.model.Module;
import com.fh.model.Permission;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2017/9/13.
 */
@Service
public class PermissionService {


    @Autowired
    private PermissionDao permissionDao;
    @Autowired
    private RoleService roleService;
    @Autowired
    private ModuleService moduleService;
    @Autowired
    private ModuleDao moduleDao;

    public void addDoRelate(Integer roleId, Integer moduleId, boolean checked) {

        // 基本参数验证
        AssertUtil.intIsNotEmpty(roleId, "请选择角色");
        AssertUtil.intIsNotEmpty(moduleId, "请选择模块");

        // 验证角色以及模块
        roleService.findById(roleId);
        Module module = moduleService.findById(moduleId);

        if (checked) { // 授权
            bindPermission(roleId, moduleId, module);
        } else {
            // 先把自己解绑
            permissionDao.releaseModule(roleId, moduleId);
            // 解绑子模块
            releaseSonModules(module, roleId);
            // 解绑父模块
            releaseParentModule(roleId, module);
        }

    }

    /**
     * 获取角色操作权限
     * @param substring
     * @return
     */
    public List<String> findRolePermissions(String roleIds) {
        List<String> aclValues = permissionDao.findRolePermissions(roleIds);
        return aclValues;
    }


    /**
     * 绑定权限
     * @param roleId
     * @param moduleId
     * @param module
     */
    private void bindPermission(Integer roleId, Integer moduleId, Module module) {

        List<Permission> permissions = new ArrayList<>();
        // 绑定当前模块
        build(roleId, moduleId, module.getOptValue(), permissions);

        // 绑定父模块
        if (module.getParentId() != null) { // 有父级
            setParentPermission(roleId, module, permissions);
        }
        // 绑定子模块
        bindSonModules(module, roleId, permissions);

        // 批量保存
        permissionDao.insertBatch(permissions);
    }

    /**
     * 设置父权限
     * @param roleId
     * @param module
     * @param permissions
     */
    private void setParentPermission(Integer roleId, Module module, List<Permission> permissions) {
        String treePath = module.getTreePath();
        // ,1,2,
        String ancestorIds = treePath.substring(treePath.indexOf(",") + 1, treePath.lastIndexOf(","));
        List<Module> ancestorModules = moduleDao.findByIds(ancestorIds);
        for (Module ancestorModule : ancestorModules) {
            Integer count = permissionDao.count(roleId, ancestorModule.getId()); // 查看是否已绑定
            if (count == null || count == 0) {
                build(roleId, ancestorModule.getId(), ancestorModule.getOptValue(), permissions);
            }
        }
    }

    /**
     * 绑定子模块
     * @param module
     * @param roleId
     * @param permissions
     */
    private void bindSonModules(Module module, Integer roleId, List<Permission> permissions ) {

        // 先解绑
        List<Module> sonModules = releaseSonModules(module, roleId);
        if (sonModules == null || sonModules.isEmpty()) {
            return;
        }
        // 添加到list
        for (Module sunModule : sonModules) {
            // 添加到list
            build(roleId, sunModule.getId(), sunModule.getOptValue(), permissions);
        }
    }

    /**
     * 查询父模块
     * @param module
     * @return
     */
    private List<Module> findAncestorModules(Module module) {
        if (module.getGrade() == ModuleGrade.ROOT.getGrade()) {
            return Collections.emptyList();
        }
        String treePath = module.getTreePath();
        // ,1,2,
        String ancestorIds = treePath.substring(treePath.indexOf(",") + 1, treePath.lastIndexOf(","));
        List<Module> ancestorModules = moduleDao.findByIds(ancestorIds);
        return ancestorModules;
    }


    /**
     * 获取子模块
     * @param module
     * @return
     */
    private List<Module> findSonModules(Module module) {
        String treePath = null;
        if (StringUtils.isBlank(module.getTreePath())) {
            treePath = "," + module.getId() + ",";
        } else {
            treePath = module.getTreePath() + module.getId() + ",";
        }
        List<Module> sonModules = moduleDao.findSunModules(treePath);
        return sonModules;
    }

    /**
     * 解绑子模块
     * @param module
     * @param roleId
     */
    private List<Module> releaseSonModules(Module module, Integer roleId) {
        // 查询子模块
        List<Module> sonModules = findSonModules(module);
        if (sonModules == null || sonModules.isEmpty()) {
            return Collections.emptyList(); // new ArrayList<>();
        }
        // 先把所有的子模块解绑
        String moduleIds = foreachModules(sonModules);
        // 解绑
        permissionDao.releaseModules(roleId, moduleIds);
        return sonModules;
    }

    /**
     * 获取ids
     * @param modules
     * @return
     */
    private static String foreachModules(List<Module> modules) {
        if (modules == null || modules.isEmpty()) {
            return ""; // new ArrayList<>();
        }
        // 先把所有的子模块解绑
        StringBuffer moduleIds = new StringBuffer();
        for (Module sunModule : modules) {
            moduleIds.append(sunModule.getId()).append(",");
        }
        return moduleIds.substring(0, moduleIds.length() - 1);
    }

    /**
     * 解绑父模块
     * @param roleId
     * @param module
     */
    private void releaseParentModule(Integer roleId, Module module) {
        List<Module> parentModules = findAncestorModules(module);
        for (Module parentModule : parentModules) {
            List<Module> sonModules = findSonModules(parentModule);
            if (sonModules == null || sonModules.isEmpty()) {
                continue;
            }
            String moduleIds = foreachModules(sonModules);
            Integer count = permissionDao.countByIds(roleId, moduleIds);
            if (count != null && count > 0) { // 子模块有关联此角色
                continue;
            }
            permissionDao.releaseModule(roleId, parentModule.getId());
        }
    }

    private static void build(Integer roleId, Integer moduleId,
                              String optValue, List<Permission> permissions) {
        Permission permission = new Permission();
        permission.setRoleId(roleId);
        permission.setModuleId(moduleId);
        permission.setAclValue(optValue);
        permissions.add(permission);
    }

}
