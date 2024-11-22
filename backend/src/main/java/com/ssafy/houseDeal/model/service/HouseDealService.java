package com.ssafy.houseDeal.model.service;

import java.util.List;

import com.ssafy.houseDeal.model.HouseDealDto;
import com.ssafy.houseDeal.model.request.GetHouseDealRequest;
import com.ssafy.houseDeal.model.request.GetHouseDealWithKeywordRequest;
import com.ssafy.houseDeal.model.response.GetHouseDealWithKeywordResponse;

public interface HouseDealService {
    List<HouseDealDto> getHouseDeals(GetHouseDealRequest request);
    
    List<GetHouseDealWithKeywordResponse> getHouseDealsWithKeyword(GetHouseDealWithKeywordRequest request);

    int getCountHouseDealsWithKeyword(String type, String code);
}
