package com.ssafy.model.dao;

import com.ssafy.model.dto.FavoriteArea;
import com.ssafy.model.dto.HouseDeal;

import java.util.List;

public interface FavoriteAreaDao {
    void insertFavoriteArea(FavoriteArea favoriteArea);
    void deleteFavoriteArea(int favoriteAreaId);
    List<FavoriteArea> getFavoriteAreasByUserId(int userInfoId);
    List<HouseDeal> searchDealsByFavoriteArea(String sidoName, String gunguName, String dongName);
}
