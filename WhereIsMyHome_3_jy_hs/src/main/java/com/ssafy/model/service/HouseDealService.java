package com.ssafy.model.service;

import com.ssafy.model.dto.HouseDeal;

import java.util.List;

public interface HouseDealService {

    // 1. 데이터 삽입
    void addHouseDeal(HouseDeal houseDealDto);

    // 2. 전체 데이터 조회
    List<HouseDeal> searchListAll();

    // 3. 조건 데이터 조회(시,구,동)
    List<HouseDeal> searchListByLocation(String sidoCode, String gugunCode, String dongCode);
    
    // 키워드 검색
	List<HouseDeal> searchListByKeyword(String keyword, String year);
    
}
