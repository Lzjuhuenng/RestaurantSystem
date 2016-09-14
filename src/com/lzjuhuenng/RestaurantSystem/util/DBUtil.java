package com.lzjuhuenng.RestaurantSystem.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
	public DBUtil() {
		// TODO Auto-generated constructor stub
	}


	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/restaurant?useUnicode=true&CharacterEncoding=utf-8", "root", "root");
		return conn;
	}


	public static void closeResultSet(ResultSet rs) throws SQLException {
		if (rs != null) {
			rs.close();
		}
	}


	public static void closeStatment(Statement stat) throws SQLException {
		if (stat != null) {
			stat.close();
		}
	}


	public static void closeConnection(Connection conn) throws SQLException {
		if (conn != null) {
			conn.close();
		}
	}

}
