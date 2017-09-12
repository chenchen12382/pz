package com.fh.service;

import com.fh.base.AssertUtil;
import com.fh.dao.RoleDao;
import com.fh.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/11.
 */
@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;


    public Map<String,Object> findAll() {
        List<Role> roles = roleDao.findAll();
        Map<String,Object> result = new HashMap<>();
        result.put("rows",roles);
        return result;

    }

    public void add(String roleName, String roleRemark) {
        AssertUtil.isNotEmpty(roleName,"请填写角色名称");
        AssertUtil.isNotEmpty(roleRemark,"请填写描述");
        Role roles = roleDao.findByName(roleName.trim());
        AssertUtil.isTrue(roles != null , "该角色已存在");
        Role role = new Role();
        role.setRoleName(roleName);
        role.setRoleRemark(roleRemark);
        roleDao.insert(role);

    }

    public void update(Role role) {
        AssertUtil.intIsNotEmpty(role.getId(),"请选择记录进行更新");
        AssertUtil.isNotEmpty(role.getRoleName(),"请填写角色名称");
        AssertUtil.isNotEmpty(role.getRoleRemark(),"请填写描述");
        Role roles = findById(role.getId());
        if(!roles.getRoleName().equals(role.getRoleName())){
            //验证基本信息
            Role roleByName = roleDao.findByName(role.getRoleName());
            AssertUtil.isTrue(roleByName != null,"该角色已存在");
        }

        roleDao.update(role);


    }

    /**
     * 检查ID是否存在
     * @param id
     * @return
     */
    private Role findById(Integer id) {

        AssertUtil.intIsNotEmpty(id, "请选择角色");
        Role role = roleDao.findById(id);
        AssertUtil.notNull(role, "该角色不存在");
        return role;

    }

    /**
     * 删除
     * @param ids
     */
    public void delete(String ids) {
        AssertUtil.isNotEmpty(ids,"请选择记录进行删除");
        roleDao.deleteBatch(ids);

    }
}
