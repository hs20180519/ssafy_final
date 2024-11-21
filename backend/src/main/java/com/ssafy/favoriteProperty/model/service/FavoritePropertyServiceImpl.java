package com.ssafy.favoriteProperty.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.favoriteProperty.model.FavoritePropertyDetailsDto;
import com.ssafy.favoriteProperty.model.FavoritePropertyDto;
import com.ssafy.favoriteProperty.model.mapper.FavoritePropertyMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FavoritePropertyServiceImpl implements FavoritePropertyService {

	private final FavoritePropertyMapper favoritePropertyMapper;

	@Override
	public void addFavoriteProperty(FavoritePropertyDto favoritePropertyDto) {
		favoritePropertyMapper.insertFavoriteProperty(favoritePropertyDto);
	}

	@Override
	public List<FavoritePropertyDetailsDto> getFavoriteProperties(String memberId) {
		return favoritePropertyMapper.selectFavoritePropertiesByMemberId(memberId);
	}

	@Override
	public void deleteFavoriteProperty(String favoritePropertyId) {
		favoritePropertyMapper.deleteFavoriteProperty(favoritePropertyId);
	}
}
