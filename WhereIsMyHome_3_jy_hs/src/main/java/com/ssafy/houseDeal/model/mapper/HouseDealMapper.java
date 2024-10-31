package com.ssafy.houseDeal.model.mapper;

import com.ssafy.houseDeal.model.HouseDealDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface HouseDealMapper {
    List<HouseDealDto> getHouseDeals(Map map);

}
