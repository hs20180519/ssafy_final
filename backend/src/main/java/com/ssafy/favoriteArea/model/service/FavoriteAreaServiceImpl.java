package com.ssafy.favoriteArea.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.favoriteArea.model.FavoriteAreaDto;
import com.ssafy.favoriteArea.model.mapper.FavoriteAreaMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FavoriteAreaServiceImpl implements FavoriteAreaService{

    private final FavoriteAreaMapper favoriteAreaMapper;

    @Override
    public List<FavoriteAreaDto> findFavoriteAreaByMemberId(String memberId) throws Exception {
        return favoriteAreaMapper.getFavoriteAreaByMemberId(memberId);
    }

    @Override
    public void addFavoriteArea(Map<String, String> request) throws Exception {
        favoriteAreaMapper.insertFavoriteArea(request);
    }

    @Override
    public void removeFavoriteArea(int favoriteAreaId) throws Exception {
        favoriteAreaMapper.deleteFavoriteArea(favoriteAreaId);
    }
}
