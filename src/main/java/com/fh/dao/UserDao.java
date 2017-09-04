package com.fh.dao;

import com.fh.model.User;

public interface UserDao {

	User findByUserName(String trim);

}
