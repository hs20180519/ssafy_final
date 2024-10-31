package com.ssafy.model.service;

import com.ssafy.model.dto.FavoriteArea;
import com.ssafy.model.dto.HouseDeal;

import java.util.List;

public interface FavoriteAreaService {
    void addFavoriteArea(FavoriteArea favoriteArea);
    void deleteFavoriteArea(int favoriteAreaId);
    List<FavoriteArea> getFavoriteAreasByUserId(int userInfoId);

    List<HouseDeal> searchDealsByFavoriteArea(String sidoName, String gunguName, String dongName);

}
