package com.ssafy.favoriteProperty.model;

import lombok.Data;

@Data
public class FavoritePropertyDetailsDto {
	private int id;
	private String memberId;
	private String aptSeq;
	private String aptNm;
	private String landAddress;
	private String roadAddress;
	private String dealAmount;
	private int dealYear;
	private int dealMonth;
	private int dealDay;
}
