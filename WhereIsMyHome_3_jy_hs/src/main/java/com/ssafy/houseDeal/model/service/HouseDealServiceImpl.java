package com.ssafy.houseDeal.model.service;

import com.ssafy.houseDeal.model.HouseDealDto;
import com.ssafy.houseDeal.model.mapper.HouseDealMapper;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class HouseDealServiceImpl implements HouseDealService {

    private final HouseDealMapper houseDealMapper;

    @Override
    public List<HouseDealDto> getHouseDeals(Map<String, String> criteria) {
        return houseDealMapper.getHouseDeals(criteria);
    }
}
