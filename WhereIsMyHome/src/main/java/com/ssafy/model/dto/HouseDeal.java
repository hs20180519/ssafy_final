package com.ssafy.model.dto;

public class HouseDeal {
	private int no;
	private String aptSeq;
	private String aptDong;
	private String floor;
	private int dealYear;
	private int dealMonth;
	private int dealDay;
	private float excluUseAr;
	private String dealAmount;
	private String sggCd;
	private String umdCd;
	private String jibun;
	private String roadNmSggCd;
	private String roadNm;
	private String roadNmBonbun;
	private String roadNmBubun;
	private String aptNm;
	private int buildYear;
	private String latitude;
	private String longitude;
	
	public HouseDeal() {
		// TODO Auto-generated constructor stub
	}

	public HouseDeal(int no, String aptSeq, String aptDong, String floor, int dealYear, int dealMonth, int dealDay,
			float excluUseAr, String dealAmount, String sggCd, String umdCd, String jibun, String roadNmSggCd,
			String roadNm, String roadNmBonbun, String roadNmBubun, String aptNm, int buildYear, String latitude,
			String longitude) {
		super();
		this.no = no;
		this.aptSeq = aptSeq;
		this.aptDong = aptDong;
		this.floor = floor;
		this.dealYear = dealYear;
		this.dealMonth = dealMonth;
		this.dealDay = dealDay;
		this.excluUseAr = excluUseAr;
		this.dealAmount = dealAmount;
		this.sggCd = sggCd;
		this.umdCd = umdCd;
		this.jibun = jibun;
		this.roadNmSggCd = roadNmSggCd;
		this.roadNm = roadNm;
		this.roadNmBonbun = roadNmBonbun;
		this.roadNmBubun = roadNmBubun;
		this.aptNm = aptNm;
		this.buildYear = buildYear;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getAptSeq() {
		return aptSeq;
	}

	public void setAptSeq(String aptSeq) {
		this.aptSeq = aptSeq;
	}

	public String getAptDong() {
		return aptDong;
	}

	public void setAptDong(String aptDong) {
		this.aptDong = aptDong;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public int getDealYear() {
		return dealYear;
	}

	public void setDealYear(int dealYear) {
		this.dealYear = dealYear;
	}

	public int getDealMonth() {
		return dealMonth;
	}

	public void setDealMonth(int dealMonth) {
		this.dealMonth = dealMonth;
	}

	public int getDealDay() {
		return dealDay;
	}

	public void setDealDay(int dealDay) {
		this.dealDay = dealDay;
	}

	public float getExcluUseAr() {
		return excluUseAr;
	}

	public void setExcluUseAr(float excluUseAr) {
		this.excluUseAr = excluUseAr;
	}

	public String getDealAmount() {
		return dealAmount;
	}

	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}

	public String getSggCd() {
		return sggCd;
	}

	public void setSggCd(String sggCd) {
		this.sggCd = sggCd;
	}

	public String getUmdCd() {
		return umdCd;
	}

	public void setUmdCd(String umdCd) {
		this.umdCd = umdCd;
	}

	public String getJibun() {
		return jibun;
	}

	public void setJibun(String jibun) {
		this.jibun = jibun;
	}

	public String getRoadNmSggCd() {
		return roadNmSggCd;
	}

	public void setRoadNmSggCd(String roadNmSggCd) {
		this.roadNmSggCd = roadNmSggCd;
	}

	public String getRoadNm() {
		return roadNm;
	}

	public void setRoadNm(String roadNm) {
		this.roadNm = roadNm;
	}

	public String getRoadNmBonbun() {
		return roadNmBonbun;
	}

	public void setRoadNmBonbun(String roadNmBonbun) {
		this.roadNmBonbun = roadNmBonbun;
	}

	public String getRoadNmBubun() {
		return roadNmBubun;
	}

	public void setRoadNmBubun(String roadNmBubun) {
		this.roadNmBubun = roadNmBubun;
	}

	public String getAptNm() {
		return aptNm;
	}

	public void setAptNm(String aptNm) {
		this.aptNm = aptNm;
	}

	public int getBuildYear() {
		return buildYear;
	}

	public void setBuildYear(int buildYear) {
		this.buildYear = buildYear;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}


	@Override
	public String toString() {
		return "아파트 시퀀스: " + aptSeq +
				", 층: " + floor +
				", 거래 연도: " + dealYear +
				", 거래 월: " + dealMonth +
				", 거래 일: " + dealDay +
				", 전용 면적: " + excluUseAr + "㎡" +
				", 거래 금액: " + dealAmount +
				", 시군구 코드: " + sggCd +
				", 읍면동 코드: " + umdCd;
	}
}
