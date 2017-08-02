package com.zhongda.quote.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

public class JdbcUtil {

	public static int executeUpdate(String sqlstr, Object... params) {
		Connection conn = DBUtil.getConn();
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sqlstr);
			for (int i = 1; i <= params.length; i++) {
				pstmt.setObject(i, params[i - 1]);
			}

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(null, pstmt, null, conn);
		}
		return result;
	}

	public static <T> List<T> executeQuery(String sqlstr, Class<T> classes,
			Object... params) {
		Connection conn = DBUtil.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<T> list = null;
		try {
			pstmt = conn.prepareStatement(sqlstr);
			for (int i = 1; i <= params.length; i++) {
				pstmt.setObject(i, params[i - 1]);
			}

			rs = pstmt.executeQuery();
			ResultSetMetaData rsm = rs.getMetaData();
			int col_count = rsm.getColumnCount();
			list = new ArrayList<T>();
			while (rs.next()) {
				T obj = classes.newInstance();
				Map<String, Object> map = new HashMap<String, Object>();
				for (int j = 1; j <= col_count; j++) {
					String cl = rsm.getColumnLabel(j);
					Object o = rs.getObject(j);
					map.put(cl, o);
				}
				BeanUtils.populate(obj, map);
				list.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(null, pstmt, rs, conn);
		}
		return list;
	}
}
