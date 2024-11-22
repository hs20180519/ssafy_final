package com.ssafy.dongCode.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.dongCode.model.DongCodeDto;
import com.ssafy.dongCode.model.request.SearchRequest;
import com.ssafy.dongCode.model.response.HouseDealSearchResult;

@Mapper
public interface DongCodeMapper {
	List<DongCodeDto> getFilters(String parentCode);
	List<HouseDealSearchResult> getHouseByKeyword(SearchRequest request);
	List<DongCodeDto> getDongCodeByKeyword(SearchRequest request);
}
