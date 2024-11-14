package com.ssafy.dongCode.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.dongCode.model.DongCodeDto;

@Mapper
public interface DongCodeMapper {
	List<DongCodeDto> getFilters(String parentCode);
}
