package com.ssafy.favoriteArea.model;

public class FavoriteAreaDto {
    private int id;
    private String memberId;
    private String dongCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getDongCode() {
        return dongCode;
    }

    public void setDongCode(String dongCode) {
        this.dongCode = dongCode;
    }
}
