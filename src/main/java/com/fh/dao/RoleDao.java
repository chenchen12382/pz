package com.fh.dao;

import com.fh.model.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Administrator on 2017/9/11.
 */
public interface RoleDao {

    @Select("select id, role_name, role_remark, create_date, "
            + "update_date from t_role where is_valid = 1")
    List<Role> findAll();

}
