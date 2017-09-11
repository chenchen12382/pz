package com.fh.dao;

import com.fh.model.UserRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/9/11.
 */
public interface UserRoleDao {


    void insertBatch(@Param(value = "userRoles") List<UserRole> userRoles);

    @Delete("delete from t_user_role where user_id = #{userId}")
    void deleteUserRoles(@Param(value = "userId") Integer UserId);
}
