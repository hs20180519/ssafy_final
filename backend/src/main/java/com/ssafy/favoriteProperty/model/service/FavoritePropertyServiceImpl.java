package com.ssafy.favoriteProperty.model.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ssafy.address.model.AddressDto;
import com.ssafy.address.model.mapper.AddressMapper;
import com.ssafy.favoriteProperty.model.DistanceInfoDto;
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
		// 회원의 관심 매물 정보 가져오기
		List<FavoritePropertyDetailsDto> properties = favoritePropertyMapper
			.selectFavoritePropertiesByMemberId(memberId);

		// 회원의 주소지 정보 가져오기
		List<AddressDto> addresses = addressMapper.selectAddressesByMemberId(memberId);

		// 매물에 대한 거리 계산
		for (FavoritePropertyDetailsDto property : properties) {
			// 각 매물에 대해 거리 계산 (회원의 모든 주소에 대해)
			List<DistanceInfoDto> distances = addresses.stream()
				.map(address -> {
					try {
						// 카카오 API를 통해 거리 계산
						double distance = kakaoApiUtil.calculateDistance(address.getRoadNameAddress(),
							property.getRoadAddress());

						// 소수점 2자리에서 반올림
						String formattedDistance = String.format("%.2f km", distance);

						// 새로운 DTO에 저장
						return new DistanceInfoDto(address.getName(), formattedDistance);
					} catch (Exception e) {
						e.printStackTrace(); // 예외 처리
						return new DistanceInfoDto(address.getName(), "거리 계산 실패");
					}
				})
				.collect(Collectors.toList());

			property.setDistances(distances); // DTO에 거리 정보 추가
		}

		return properties;
	}

	@Override
	public boolean existsFavoritePropertyByAptSeqAndId(String memberId, String aptSeq) {
		FavoritePropertyDto favoriteProperty = favoritePropertyMapper.getFavoritePropertyByAptSeqAndId(memberId,
			aptSeq);
		System.out.println(favoriteProperty);
		if (favoriteProperty == null) {
			return false;
		}
		return true;
	}

	@Override
	public void deleteFavoriteProperty(String memberId, String aptSeq) {
		favoritePropertyMapper.deleteFavoriteProperty(memberId, aptSeq);
	}
}
