package com.ssafy.houseDeal.model.service;

import com.ssafy.houseDeal.model.HouseDealDto;

import java.util.List;
import java.util.Map;

public interface HouseDealService {
    List<HouseDealDto> getHouseDeals(Map<String, String> criteria);
    
    List<HouseDealDto> getHouseDealsWithKeyword(String type, String code);
}
