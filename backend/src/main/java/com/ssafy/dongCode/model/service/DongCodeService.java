package com.ssafy.dongCode.model.service;

import java.util.List;

import com.ssafy.dongCode.model.DongCodeDto;
import com.ssafy.dongCode.model.response.SearchResultResponse;

public interface DongCodeService {
	List<DongCodeDto> getFilters(String parentCode);

	SearchResultResponse searchByKeyword(String keyword);
}
