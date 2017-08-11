package com.zhongda.quote.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zhongda.quote.utils.MyBatisUtil;

public class TestSQLite {

	public static void main(String[] args) throws SQLException {
		Connection conn = MyBatisUtil.getSqlSession().getConnection();

		String sql = "insert into QuoteTask (task_number, task_name,task_description, industry, create_user,create_date, last_update_date, task_amount) values ('ZD201708080002','Test任务','呵呵',1,'zmdeng',datetime('now'),datetime('now'),0);";
		PreparedStatement prepareStatement = conn.prepareStatement(sql);

		int execute = prepareStatement.executeUpdate();
		System.out.println(execute+"========================");

		conn.commit();

		PreparedStatement prepareStatement2 = conn.prepareStatement("select * from QuoteTask;");
		 ResultSet rs = prepareStatement2.executeQuery();

		System.out.println("创建表结构录入数据操作演示：");
		while (rs.next()) { // 将查询到的数据打印出来
			System.out.print("task_name = " + rs.getString("task_name") + ", "); // 列属性一
			System.out.println("task_number = " + rs.getString("task_number")); // 列属性二
		}
		rs.close();
		conn.close(); // 结束数据库的连接
	}

}
