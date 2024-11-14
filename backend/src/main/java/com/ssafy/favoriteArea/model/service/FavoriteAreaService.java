package com.ssafy.favoriteArea.model.service;

import com.ssafy.favoriteArea.model.FavoriteAreaDto;

import java.util.List;
import java.util.Map;

public interface FavoriteAreaService {
    List<FavoriteAreaDto> findFavoriteAreaByMemberId(String memberId) throws Exception;
    void addFavoriteArea(Map<String, String> request) throws Exception;

    void removeFavoriteArea(int favoriteAreaId) throws Exception;
}
