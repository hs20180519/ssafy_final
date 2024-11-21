package com.ssafy.favoriteProperty.model.mapper;

import java.util.List;

import com.ssafy.favoriteProperty.model.FavoritePropertyDetailsDto;
import com.ssafy.favoriteProperty.model.FavoritePropertyDto;

public interface FavoritePropertyMapper {
	void insertFavoriteProperty(FavoritePropertyDto favoritePropertyDto); // 관심 매물 추가

	List<FavoritePropertyDetailsDto> selectFavoritePropertiesByMemberId(String memberId); // 관심 매물 조회

	void deleteFavoriteProperty(String favoritePropertyId); // 관심 매물 삭제
}
