package com.ssafy.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.model.dto.UserInfo;
import com.ssafy.util.DBUtil;

public class UserInfoDaoImpl implements UserInfoDao {
	private static DBUtil dbUtil = DBUtil.getInstance();
	
	private static UserInfoDao userInfoDao = new UserInfoDaoImpl();
	private UserInfoDaoImpl() {}
	
	public static UserInfoDao getInstance() {
		return userInfoDao;
	}

	@Override
	public int insertUser(UserInfo user) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dbUtil.getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("insert into user_infos(user_email, user_password, user_name) \n");
			sql.append("values (?, ?, ?)");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1,  user.getUserEmail());
			pstmt.setString(2, user.getUserPassword());
			pstmt.setString(3, user.getUserName());
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, conn);
		}
		return 0;
	}

	@Override
	public int updateUserName(String userEmail, String modifyName) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dbUtil.getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("update user_infos \n");
			sql.append("set user_name = ? where user_email = ?");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, modifyName);
			pstmt.setString(2, userEmail);
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, conn);
		}
		return 0;
	}

	@Override
	public int updateUserPwd(String userEmail, String userName, String modifyPwd) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dbUtil.getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("update user_infos set user_password = ? where user_name= ? and user_email= ?");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, modifyPwd);
			pstmt.setString(2, userName);
			pstmt.setString(3, userEmail);
			
			return pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, conn);
		}
		return 0;
	}

	@Override
	public int deleteMember(String userEmail) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dbUtil.getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("delete from user_infos where user_email=?");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, userEmail);
			return pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, conn);
		}
		return 0;
	}

	@Override
	public String findPwdByEmail(String email) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dbUtil.getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("select user_password from user_infos where user_email = ?");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, email);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return rs.getString("user_password");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(conn, pstmt, rs);
		}
		return null;
	}

	@Override
	public List<UserInfo> selectAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<UserInfo> list = new ArrayList<>();
		
		try {
			conn = dbUtil.getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("select * from user_infos");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			// sql 실행
			rs = pstmt.executeQuery();
			
			list = new ArrayList<>();
			UserInfo user = null;
			
			while(rs.next()) {
				user = new UserInfo();
				
				user.setUserInfoId(rs.getInt("user_info_id")); // rs.getString(1);
				user.setUserName(rs.getString("user_name"));
				user.setUserEmail(rs.getString("user_email"));
				user.setUserPassword(rs.getString("user_password"));
				
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(conn, pstmt, rs);
		}
		return list;
	}

	@Override
	public List<UserInfo> selectAllByUserName(String searchName) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<UserInfo> list = new ArrayList<>();
		
		try {
			conn = dbUtil.getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("select * from user_infos where user_name like ?");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, "%" + searchName + "%");
			
			// sql 실행
			rs = pstmt.executeQuery();
			
			list = new ArrayList<>();
			UserInfo user = null;
			
			while(rs.next()) {
				user = new UserInfo();
				
				user.setUserInfoId(rs.getInt("user_info_id")); // rs.getString(1);
				user.setUserName(rs.getString("user_name"));
				user.setUserEmail(rs.getString("user_email"));
				user.setUserPassword(rs.getString("user_password"));
				
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(conn, pstmt, rs);
		}
		return list;
	}

	@Override
	public UserInfo selectByUserEmail(String userEmail) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dbUtil.getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("select * from user_infos where user_email = ?");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, userEmail);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				UserInfo user = new UserInfo();
				user.setUserEmail(userEmail);
				user.setUserInfoId(rs.getInt("user_info_id"));
				user.setUserName(rs.getString("user_name"));
				user.setUserPassword(rs.getString("user_password"));
				
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(conn, pstmt, rs);
		}
		return null;
	}

	@Override
	public UserInfo selectByUserEmailAndUserNameAndPwd(String userName, String userEmail, String userPwd) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dbUtil.getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("select * from user_infos where user_email = ? and user_name = ? and user_password = ?");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, userEmail);
			pstmt.setString(2, userName);
			pstmt.setString(3, userPwd);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				UserInfo user = new UserInfo();
				user.setUserEmail(userEmail);
				user.setUserInfoId(rs.getInt("user_info_id"));
				user.setUserName(userName);
				user.setUserPassword(userPwd);
				
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(conn, pstmt, rs);
		}
		return null;
	}

	@Override
	public UserInfo selectByUserEmailAndPwd(String userEmail, String userPwd) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dbUtil.getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("select * from user_infos where user_email = ? and user_password = ?");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, userEmail);
			pstmt.setString(2, userPwd);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				UserInfo user = new UserInfo();
				user.setUserEmail(userEmail);
				user.setUserInfoId(rs.getInt("user_info_id"));
				user.setUserName(rs.getString("user_name"));
				user.setUserPassword(userPwd);
				
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(conn, pstmt, rs);
		}
		return null;
	}

}
