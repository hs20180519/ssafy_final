package com.ssafy.dongCode.model.service;

import java.util.List;

import com.ssafy.dongCode.model.response.HouseDealSearchResult;
import com.ssafy.dongCode.model.response.SearchResultResponse;
import org.springframework.stereotype.Service;

import com.ssafy.dongCode.model.DongCodeDto;
import com.ssafy.dongCode.model.mapper.DongCodeMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DongCodeServiceImpl implements DongCodeService {
	
	private final DongCodeMapper dongCodeMapper;

	@Override
	public List<DongCodeDto> getFilters(String parentCode) {
		return dongCodeMapper.getFilters(parentCode);
	}

	@Override
	public SearchResultResponse searchByKeyword(String keyword) {
		List<HouseDealSearchResult> houses = dongCodeMapper.getHouseByKeyword(keyword);
		List<DongCodeDto> regions = dongCodeMapper.getDongCodeByKeyword(keyword);

		return SearchResultResponse.builder()
				.regions(regions)
				.houses(houses)
				.build();
	}

}
