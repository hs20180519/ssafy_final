package com.ssafy.houseDeal.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.houseDeal.model.HouseDealDto;
import com.ssafy.houseDeal.model.mapper.HouseDealMapper;
import com.ssafy.houseDeal.model.request.GetHouseDealRequest;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class HouseDealServiceImpl implements HouseDealService {

    private final HouseDealMapper houseDealMapper;

    @Override
    public List<HouseDealDto> getHouseDeals(GetHouseDealRequest request) {
    	int offset = (request.getPgno() - 1) * request.getPgSize();
    	request.setOffset(offset);
        return houseDealMapper.getHouseDeals(request);
    }

	@Override
	public List<HouseDealDto> getHouseDealsWithKeyword(String type, String code) {
		// TODO Auto-generated method stub
		System.out.println(code + " " + type);
		return houseDealMapper.getHouseDealsWithKeyword(type, code);
	}
}
