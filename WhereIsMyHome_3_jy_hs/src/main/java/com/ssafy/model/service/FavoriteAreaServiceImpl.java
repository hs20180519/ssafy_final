package com.ssafy.model.service;

import com.ssafy.model.dao.FavoriteAreaDao;
import com.ssafy.model.dao.FavoriteAreaDaoImpl;
import com.ssafy.model.dto.FavoriteArea;
import com.ssafy.model.dto.HouseDeal;

import java.util.List;

public class FavoriteAreaServiceImpl implements FavoriteAreaService{
    private FavoriteAreaDao favoriteAreaDao = FavoriteAreaDaoImpl.getInstance();
    private static FavoriteAreaServiceImpl instance = new FavoriteAreaServiceImpl();

    private FavoriteAreaServiceImpl() {}

    public static FavoriteAreaServiceImpl getInstance() {
        return instance;
    }

    @Override
    public void addFavoriteArea(FavoriteArea favoriteArea) {
        favoriteAreaDao.insertFavoriteArea(favoriteArea);
    }

    @Override
    public void deleteFavoriteArea(int favoriteAreaId) {
        favoriteAreaDao.deleteFavoriteArea(favoriteAreaId);
    }

    @Override
    public List<FavoriteArea> getFavoriteAreasByUserId(int userInfoId) {
        return favoriteAreaDao.getFavoriteAreasByUserId(userInfoId);
    }
    @Override
    public List<HouseDeal> searchDealsByFavoriteArea(String sidoName, String gunguName, String dongName) {
        return favoriteAreaDao.searchDealsByFavoriteArea(sidoName, gunguName, dongName);
    }
}
