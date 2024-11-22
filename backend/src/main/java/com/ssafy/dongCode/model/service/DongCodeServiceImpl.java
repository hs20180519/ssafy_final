package com.ssafy.dongCode.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.dongCode.model.DongCodeDto;
import com.ssafy.dongCode.model.mapper.DongCodeMapper;
import com.ssafy.dongCode.model.request.SearchRequest;
import com.ssafy.dongCode.model.response.HouseDealSearchResult;
import com.ssafy.dongCode.model.response.SearchResultResponse;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class DongCodeServiceImpl implements DongCodeService {
	
	private final DongCodeMapper dongCodeMapper;

	@Override
	public List<DongCodeDto> getFilters(String parentCode) {
		return dongCodeMapper.getFilters(parentCode);
	}

	@Override
	public SearchResultResponse searchByKeyword(SearchRequest request) {
		List<HouseDealSearchResult> houses = dongCodeMapper.getHouseByKeyword(request);
		List<DongCodeDto> regions = dongCodeMapper.getDongCodeByKeyword(request);

		return SearchResultResponse.builder()
				.regions(regions)
				.houses(houses)
				.build();
	}

}
