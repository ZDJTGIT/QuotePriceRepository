package com.zhongda.quote.dao.impl;

import java.util.List;

import com.zhongda.quote.dao.UserDao;
import com.zhongda.quote.model.User;
import com.zhongda.quote.utils.JdbcUtil;

public class UserDaoImpl implements UserDao{

	public List<User> selectUserList() {
		List<User> list = JdbcUtil.executeQuery("select * from user", User.class, new Object[]{});
		return list;
	}

	@Override
	public List<User> selectUserListByName(String string) {
		List<User> list = JdbcUtil.executeQuery("select * from user where name = ?", User.class, string);
		return list;
	}

}
