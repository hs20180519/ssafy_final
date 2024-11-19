package com.ssafy.houseDeal.request;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class GetHouseDealRequest {
	private String sidoCode;
	private String gugunCode;
	private String dongCode;
	private String keyword;
}
