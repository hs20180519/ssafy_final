package com.ssafy.houseDeal.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.houseDeal.model.HouseDealDto;
import com.ssafy.houseDeal.request.GetHouseDealRequest;

@Mapper
public interface HouseDealMapper {
    List<HouseDealDto> getHouseDeals(GetHouseDealRequest critera);
    List<HouseDealDto> getHouseDealsWithKeyword(@Param("type") String type, @Param("code") String code);
}
