package com.ssafy.dongCode.model.mapper;

import java.util.List;

import com.ssafy.dongCode.model.response.HouseDealSearchResult;
import org.apache.ibatis.annotations.Mapper;

import com.ssafy.dongCode.model.DongCodeDto;

@Mapper
public interface DongCodeMapper {
	List<DongCodeDto> getFilters(String parentCode);
	List<HouseDealSearchResult> getHouseByKeyword(String keyword);
	List<DongCodeDto> getDongCodeByKeyword(String keyword);
}
