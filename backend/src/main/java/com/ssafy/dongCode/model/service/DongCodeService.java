package com.ssafy.dongCode.model.service;

import java.util.List;

import com.ssafy.dongCode.model.DongCodeDto;

public interface DongCodeService {
	List<DongCodeDto> getFilters(String parentCode);
}
