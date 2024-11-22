package com.ssafy.dongCode.model.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchRequest {
	private String sidoCode;
	private String gugunCode;
	private String dongCode;
	private String keyword;
}
