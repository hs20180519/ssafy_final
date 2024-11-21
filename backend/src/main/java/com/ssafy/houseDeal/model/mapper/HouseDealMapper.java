package com.ssafy.houseDeal.model.mapper;

import java.util.List;

import com.ssafy.houseDeal.model.request.GetHouseDealWithKeywordRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.houseDeal.model.HouseDealDto;
import com.ssafy.houseDeal.model.request.GetHouseDealRequest;

@Mapper
public interface HouseDealMapper {
    List<HouseDealDto> getHouseDeals(GetHouseDealRequest critera);
    List<HouseDealDto> getHouseDealsWithKeyword(GetHouseDealWithKeywordRequest request);
    int getCountHouseDealsWithKeyword(@Param("type") String type, @Param("code") String code);
}
