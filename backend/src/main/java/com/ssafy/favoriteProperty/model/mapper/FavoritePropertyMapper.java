package com.ssafy.favoriteProperty.model.mapper;

import java.util.List;

import com.ssafy.favoriteProperty.model.FavoritePropertyDetailsDto;
import com.ssafy.favoriteProperty.model.FavoritePropertyDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FavoritePropertyMapper {
	void insertFavoriteProperty(FavoritePropertyDto favoritePropertyDto); // 관심 매물 추가

	List<FavoritePropertyDetailsDto> selectFavoritePropertiesByMemberId(String memberId); // 관심 매물 전체 조회

	FavoritePropertyDto getFavoritePropertyByAptSeqAndId(@Param("memberId") String memberId, @Param("aptSeq") String aptSeq);

	void deleteFavoriteProperty(@Param("memberId") String memberId, @Param("aptSeq") String aptSeq); // 관심 매물 삭제
}
