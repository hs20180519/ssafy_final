package com.ssafy.model.dao;

import java.util.List;

import com.ssafy.model.dto.UserInfo;

public interface UserInfoDao {
	int insertUser(UserInfo user);
	
	int updateUserName(String userEmail, String modifyName);
	int updateUserPwd(String userEmail, String userPwd, String modifyPwd);
	
	int deleteMember(String userEmail);
	
	String findPwdByEmail(String email);
	
	List<UserInfo> selectAll();
	List<UserInfo> selectAllByUserName(String searchName);
	UserInfo selectByUserEmail(String userEmail);
	UserInfo selectByUserEmailAndUserNameAndPwd(String userName, String userEmail, String userPwd);
	UserInfo selectByUserEmailAndPwd(String userEmail, String password);
}
