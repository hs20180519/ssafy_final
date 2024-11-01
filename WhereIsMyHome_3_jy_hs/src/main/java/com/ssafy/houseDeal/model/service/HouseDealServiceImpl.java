package com.ssafy.houseDeal.model.service;

import com.ssafy.houseDeal.model.HouseDealDto;
import com.ssafy.houseDeal.model.mapper.HouseDealMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class HouseDealServiceImpl implements HouseDealService {

    HouseDealMapper houseDealMapper;

    public HouseDealServiceImpl(HouseDealMapper houseDealMapper) {
        this.houseDealMapper = houseDealMapper;
    }

    @Override
    public List<HouseDealDto> getHouseDeals(Map<String, String> criteria) {
        return houseDealMapper.getHouseDeals(criteria);
    }
}
