package com.fh.service;

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
}
