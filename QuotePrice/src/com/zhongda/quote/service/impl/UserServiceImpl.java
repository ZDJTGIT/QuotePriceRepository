package com.zhongda.quote.service.impl;

import java.util.List;

import com.zhongda.quote.dao.UserDao;
import com.zhongda.quote.dao.impl.UserDaoImpl;
import com.zhongda.quote.model.User;
import com.zhongda.quote.service.UserService;

public class UserServiceImpl implements UserService{

	private UserDao userDaoImpl = new UserDaoImpl();

	public List<User> selectUserList() {
		List<User> userList = userDaoImpl.selectUserList();
		return userList;
	}

	@Override
	public List<User> selectUserListByName(String string) {
		List<User> userList = userDaoImpl.selectUserListByName(string);
		return userList;
	}

}
