package com.ssafy.houseDeal.model.service;

import java.util.List;

import com.ssafy.houseDeal.model.HouseDealDto;
import com.ssafy.houseDeal.model.request.GetHouseDealRequest;

public interface HouseDealService {
    List<HouseDealDto> getHouseDeals(GetHouseDealRequest request);
    
    List<HouseDealDto> getHouseDealsWithKeyword(String type, String code);
}
