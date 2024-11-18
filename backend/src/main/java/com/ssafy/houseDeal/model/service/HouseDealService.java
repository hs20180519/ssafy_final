package com.ssafy.houseDeal.model.service;

import java.util.List;

import com.ssafy.houseDeal.model.HouseDealDto;
import com.ssafy.houseDeal.request.GetHouseDealRequest;

public interface HouseDealService {
    List<HouseDealDto> getHouseDeals(GetHouseDealRequest criteria);
    
    List<HouseDealDto> getHouseDealsWithKeyword(String type, String code);
}
