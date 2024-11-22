package com.ssafy.houseDeal.model.response;

import java.util.List;

import com.ssafy.houseDeal.model.HouseDealDto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetHouseDealWithKeywordResponse {
	private String aptSeq;
	private String aptNm;
	private int latestDealYear;
	private int latestDealMonth;
	private int latestDealDay;
	private String latestDealAmount;
	private String latitude;
	private String longitude;
	private List<HouseDealDto> dealList;
}
