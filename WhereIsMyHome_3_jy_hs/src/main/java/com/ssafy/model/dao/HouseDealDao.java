package com.ssafy.model.dao;

import com.ssafy.model.dto.HouseDeal;

import java.util.List;

public interface HouseDealDao {
    void insertHouseDeal(HouseDeal houseDealDto);

    List<HouseDeal> selectList();

    List<HouseDeal> selectByLocation(String sidoCode, String gugunCode, String dongCode);

    // 키워드 집 검색
    List<String> selectHouseInfo(String keyword);

	List<HouseDeal> selectRecentlyList(String dealYear);
}
