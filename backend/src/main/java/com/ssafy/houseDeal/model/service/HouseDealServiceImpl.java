package com.ssafy.houseDeal.model.service;

import java.util.List;

import com.ssafy.houseDeal.model.request.GetHouseDealWithKeywordRequest;
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
	public List<HouseDealDto> getHouseDealsWithKeyword(GetHouseDealWithKeywordRequest request) {
		int offset = (request.getPgno() - 1) * request.getPgSize();
		request.setOffset(offset);
		return houseDealMapper.getHouseDealsWithKeyword(request);
	}

	@Override
	public int getCountHouseDealsWithKeyword(String type, String code) {
		return houseDealMapper.getCountHouseDealsWithKeyword(type, code);
	}
}
