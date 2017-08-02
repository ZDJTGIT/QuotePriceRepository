package com.zhongda.quote.dao;

import java.util.List;

import com.zhongda.quote.model.User;

public interface UserDao {

	List<User> selectUserList();

	List<User> selectUserListByName(String string);
}
