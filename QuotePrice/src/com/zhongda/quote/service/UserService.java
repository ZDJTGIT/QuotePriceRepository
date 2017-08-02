package com.zhongda.quote.service;

import java.util.List;

import com.zhongda.quote.model.User;

public interface UserService {

	List<User> selectUserList();

	List<User> selectUserListByName(String string);
}
