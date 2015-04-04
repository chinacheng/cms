package com.cms.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 连接数据库
 * @author 302
 *
 */
public class DBConnection {
	
	public static Connection openConnection(){
		String driver = "com.mysql.jdbc.Driver"; // mysql驱动
		String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8&user=root&password=";
		// String url = "jdbc:mysql://192.168.1.200:3306/test?useUnicode=true&characterEncoding=UTF-8&user=root&password=";
		try {
			Class.forName(driver); // 加载驱动
		return DriverManager.getConnection(url); // 获得连接
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 关闭数据库
	 * @param conn
	 * @return
	 */
	public static boolean closeConnection(Connection conn) {
		boolean closed = false;
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
				conn = null;
				closed = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return closed;
	}
}
