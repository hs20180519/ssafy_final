package com.ssafy.favoriteArea.model.mapper;

import com.ssafy.favoriteArea.model.FavoriteAreaDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface FavoriteAreaMapper {
    List<FavoriteAreaDto> getFavoriteAreaByMemberId(String memberId) throws SQLException;
    void insertFavoriteArea(Map<String, String> request) throws SQLException;

    void deleteFavoriteArea(int favoriteAreaId) throws SQLException;
}
