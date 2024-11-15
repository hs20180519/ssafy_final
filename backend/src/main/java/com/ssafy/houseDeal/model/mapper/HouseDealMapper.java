package com.ssafy.houseDeal.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.houseDeal.model.HouseDealDto;

@Mapper
public interface HouseDealMapper {
    List<HouseDealDto> getHouseDeals(Map critera);
    List<HouseDealDto> getHouseDealsWithKeyword(@Param("type") String type, @Param("code") String code);
}
