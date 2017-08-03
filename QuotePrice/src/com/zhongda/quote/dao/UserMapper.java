package com.zhongda.quote.dao;

import java.util.List;

import com.zhongda.quote.model.User;

public interface UserMapper {

    int insert(User record);

	int insertSelective(User record);

	List<User> selectUserList();

	List<User> selectUserListByName(String string);

}