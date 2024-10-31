package com.ssafy.model.dto;

public class FavoriteArea {
	private int favoriteAreaId;
	private int userInfoId;
	private String sidoName;
	private String gunguName;
	private String dongName;
	
	public FavoriteArea() {
		// TODO Auto-generated constructor stub
	}

	public FavoriteArea(int favoriteAreaId, int userInfoId, String sidoName, String gunguName, String dongName) {
		this.favoriteAreaId = favoriteAreaId;
		this.userInfoId = userInfoId;
		this.sidoName = sidoName;
		this.gunguName = gunguName;
		this.dongName = dongName;
	}

	public int getFavoriteAreaId() {
		return favoriteAreaId;
	}

	public void setFavoriteAreaId(int favoriteAreaId) {
		this.favoriteAreaId = favoriteAreaId;
	}

	public int getUserInfoId() {
		return userInfoId;
	}

	public void setUserInfoId(int userInfoId) {
		this.userInfoId = userInfoId;
	}

	public String getSidoName() {
		return sidoName;
	}

	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
	}

	public String getGunguName() {
		return gunguName;
	}

	public void setGunguName(String gunguName) {
		this.gunguName = gunguName;
	}

	public String getDongName() {
		return dongName;
	}

	public void setDongName(String dongName) {
		this.dongName = dongName;
	}

	@Override
	public String toString() {
		return "관심 지역 ID: " + favoriteAreaId +
				", 사용자 ID: " + userInfoId +
				", 시: " + sidoName +
				", 구: " + gunguName +
				", 동: " + dongName;
	}
	
}
