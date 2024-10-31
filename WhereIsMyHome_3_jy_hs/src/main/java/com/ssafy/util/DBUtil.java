package com.ssafy.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
	private static DBUtil instance = new DBUtil();
	
	private final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private final String URL = "jdbc:mysql://localhost:3306/ssafyhome?serverTimezone=UTC";
	private final String DB_ID = "ssafy";
	private final String DB_PWD = "ssafy";
	
	/*
	 * DB 연결을 위한 property 속성 가져오기
	 */
	
	/* jDBC 드라이버 로딩 초기화*/
	private DBUtil() {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static DBUtil getInstance() {
		return instance;
	}
	
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, DB_ID, DB_PWD);
	}
	
	
	/* CUD 자원해제 */
	public void close(Statement stmt, Connection conn) {
		close(null, stmt, conn);
	}

	/* select 자원해제 */
	public void close(ResultSet rs, Statement stmt, Connection conn) {
		try {
			if(rs != null) {
				rs.close();
			}
			
			if(stmt != null) {
				stmt.close();
			}
			
			if(conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void close(AutoCloseable...closeables) {
		for(AutoCloseable closeable : closeables) {
			if(closeable != null) {
				try {
					closeable.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
