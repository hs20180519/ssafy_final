package com.ssafy.favoriteProperty.model.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ssafy.address.model.AddressDto;
import com.ssafy.address.model.mapper.AddressMapper;
import com.ssafy.favoriteProperty.model.FavoritePropertyDetailsDto;
import com.ssafy.favoriteProperty.model.FavoritePropertyDto;
import com.ssafy.favoriteProperty.model.mapper.FavoritePropertyMapper;
import com.ssafy.util.KakaoApiUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FavoritePropertyServiceImpl implements FavoritePropertyService {

	private final FavoritePropertyMapper favoritePropertyMapper;
	private final AddressMapper addressMapper;
	private final KakaoApiUtil kakaoApiUtil;

	@Override
	public void addFavoriteProperty(FavoritePropertyDto favoritePropertyDto) {
		favoritePropertyMapper.insertFavoriteProperty(favoritePropertyDto);
	}

	@Override
	public List<FavoritePropertyDetailsDto> getFavoriteProperties(String memberId) {
		// 회원의 관심 매물 정보 가져오기
		List<FavoritePropertyDetailsDto> properties = favoritePropertyMapper
			.selectFavoritePropertiesByMemberId(memberId);

		// 회원의 주소지 정보 가져오기
		List<AddressDto> addresses = addressMapper.selectAddressesByMemberId(memberId); // 주소 정보 가져오기

		// 매물에 대한 거리 계산
		for (FavoritePropertyDetailsDto property : properties) {
			// 각 매물에 대해 거리 계산 (회원의 모든 주소에 대해)
			List<String> distances = addresses.stream()
				.map(address -> {
					try {
						// 카카오 API를 통해 거리 계산
						double distance = kakaoApiUtil.calculateDistance(address.getRoadNameAddress(),
							property.getRoadAddress());

						// 소수점 3자리에서 반올림
						String formattedDistance = String.format("%.2f", distance);

						return address.getName() + "까지 " + formattedDistance + "km"; // 거리 정보 추가
					} catch (Exception e) {
						e.printStackTrace(); // 예외 처리
						return "거리 계산 실패"; // 예외 발생 시 대체 값
					}
				})
				.collect(Collectors.toList());
			property.setDistances(distances); // DTO에 거리 정보 추가
		}

		return properties;
	}

	@Override
	public void deleteFavoriteProperty(String favoritePropertyId) {
		favoritePropertyMapper.deleteFavoriteProperty(favoritePropertyId);
	}
}
