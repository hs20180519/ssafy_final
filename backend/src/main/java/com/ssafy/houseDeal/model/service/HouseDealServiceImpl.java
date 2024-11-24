package com.ssafy.houseDeal.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ssafy.houseDeal.model.HouseDealDto;
import com.ssafy.houseDeal.model.mapper.HouseDealMapper;
import com.ssafy.houseDeal.model.request.GetHouseDealRequest;
import com.ssafy.houseDeal.model.request.GetHouseDealWithKeywordRequest;
import com.ssafy.houseDeal.model.response.GetHouseDealWithKeywordResponse;

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
	public List<GetHouseDealWithKeywordResponse> getHouseDealsWithKeyword(GetHouseDealWithKeywordRequest request) {
		int offset = (request.getPgno() - 1) * request.getPgSize();
		request.setOffset(offset);
		List<HouseDealDto> houseDeals = houseDealMapper.getHouseDealsWithKeyword(request);
		
		Map<String, List<HouseDealDto>> groupedByAptSeq = houseDeals.stream()
				.collect(Collectors.groupingBy(HouseDealDto::getAptSeq));
		
		List<GetHouseDealWithKeywordResponse> result = groupedByAptSeq.entrySet().stream()
				.map(entry -> {
					String aptSeq = entry.getKey();
					List<HouseDealDto> list = entry.getValue();
					
					HouseDealDto latest = list.get(0);
					
					return GetHouseDealWithKeywordResponse.builder()
							.aptSeq(aptSeq)
							.aptNm(latest.getAptNm())
							.latestDealYear(latest.getDealYear())
							.latestDealMonth(latest.getDealMonth())
							.latestDealDay(latest.getDealDay())
							.latestDealAmount(latest.getDealAmount())
							.latitude(latest.getLatitude())
							.longitude(latest.getLongitude())
							.dealList(list)
							.build();
				})
				.collect(Collectors.toList());
		int end = Math.min(offset + request.getPgSize(), result.size());

		return result.subList(offset, end);
		
	}

	@Override
	public int getCountHouseDealsWithKeyword(String type, String code) {
		return houseDealMapper.getCountHouseDealsWithKeyword(type, code);
	}
}
