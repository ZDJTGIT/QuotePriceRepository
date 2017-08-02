package com.zhongda.quote.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

	private static String url = "jdbc:sqlite:data/QuotePrice.db";
	private static String classPath = "org.sqlite.JDBC";

	private static Connection conn = null;

	static{
		try {
			Class.forName(classPath);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConn(){
		try {
			if(null == conn){
				conn = DriverManager.getConnection(url);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void close(Statement stmt,PreparedStatement pstmt,ResultSet rs,Connection conn){
		try{
			if(null != rs){
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(null != stmt){
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					if(null != pstmt){
						pstmt.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}finally{

				}
			}
		}
	}
}
