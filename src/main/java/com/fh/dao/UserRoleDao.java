package com.fh.dao;

import com.fh.model.UserRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Administrator on 2017/9/11.
 */
public interface UserRoleDao {


    void insertBatch(@Param(value = "userRoles") List<UserRole> userRoles);

    @Delete("delete from t_user_role where user_id = #{userId}")
    void deleteUserRoles(@Param(value = "userId") Integer UserId);

    @Select("select id, user_id, role_id from t_user_role where user_id = #{userId} and is_valid = 1")
    List<UserRole> findUserRoles(Integer userId);
}
