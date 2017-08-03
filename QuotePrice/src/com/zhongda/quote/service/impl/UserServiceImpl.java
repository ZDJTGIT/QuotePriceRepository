package com.zhongda.quote.service.impl;

import java.util.List;

import com.zhongda.quote.dao.UserMapper;
import com.zhongda.quote.model.User;
import com.zhongda.quote.service.UserService;
import com.zhongda.quote.utils.MyBatisUtil;

public class UserServiceImpl implements UserService{

	private UserMapper userMapper =  MyBatisUtil.getSqlSession().getMapper(UserMapper.class);

	public List<User> selectUserList() {
		List<User> userList = userMapper.selectUserList();
		return userList;
	}

	@Override
	public List<User> selectUserListByName(String string) {
		List<User> userList = userMapper.selectUserListByName(string);
		return userList;
	}

}
