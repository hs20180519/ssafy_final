package com.ssafy.model.service;

import java.util.List;

import com.ssafy.model.dto.UserInfo;

public interface UserInfoService {
	UserInfo login(String userEmail, String password);
	void logout();
	
	boolean addUser(UserInfo user);
	
	UserInfo modifyUserName(String userEmail, String modifyName);
	
	boolean deleteUser(String userEmail);
	
	boolean isLoginUser(UserInfo user);
	
	List<UserInfo> findAll();
	List<UserInfo> findAllByUserName(String searchName);
	UserInfo findByUserEmail(String userEmail);
	UserInfo modifyPwd(String userEmail, String userName, String modifyPwd);
}
