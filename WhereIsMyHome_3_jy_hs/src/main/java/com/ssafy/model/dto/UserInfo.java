package com.ssafy.model.dto;

public class UserInfo {
	private int userInfoId;
	private String userEmail;
	private String userPassword;
	private String userName;
	
	public UserInfo() {
	}

	public UserInfo(int userInfoId, String userEmail, String userPassword, String userName) {
		this.userInfoId = userInfoId;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userName = userName;
	}

	public int getUserInfoId() {
		return userInfoId;
	}

	public void setUserInfoId(int userInfoId) {
		this.userInfoId = userInfoId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "UserInfo [userInfoId=" + userInfoId + ", userEmail=" + userEmail + ", userPassword=" + userPassword
				+ ", userName=" + userName + "]";
	}
	
	
}
