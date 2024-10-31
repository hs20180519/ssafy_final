package com.ssafy.favoriteArea.model.service;

import com.ssafy.favoriteArea.model.FavoriteAreaDto;
import com.ssafy.favoriteArea.model.mapper.FavoriteAreaMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FavoriteAreaServiceImpl implements  FavoriteAreaService{

    private final FavoriteAreaMapper favoriteAreaMapper;

    public FavoriteAreaServiceImpl(FavoriteAreaMapper favoriteAreaMapper) {
        this.favoriteAreaMapper = favoriteAreaMapper;
    }

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
