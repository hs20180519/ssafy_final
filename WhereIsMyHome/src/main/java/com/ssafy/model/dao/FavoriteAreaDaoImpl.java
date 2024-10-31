package com.ssafy.model.dao;

import com.ssafy.model.dto.FavoriteArea;
import com.ssafy.model.dto.HouseDeal;
import com.ssafy.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FavoriteAreaDaoImpl implements FavoriteAreaDao{
    private DBUtil dbUtil = DBUtil.getInstance();
    private static FavoriteAreaDaoImpl instance = new FavoriteAreaDaoImpl();

    private FavoriteAreaDaoImpl() {}

    public static FavoriteAreaDaoImpl getInstance() {
        return instance;
    }
    @Override
    public void insertFavoriteArea(FavoriteArea favoriteArea) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = dbUtil.getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO favorite_areas (member_id, sido_name, gungu_name, dong_name) ")
                    .append("VALUES (?, ?, ?, ?)");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setInt(1, favoriteArea.getUserInfoId());
            pstmt.setString(2, favoriteArea.getSidoName());
            pstmt.setString(3, favoriteArea.getGunguName());
            pstmt.setString(4, favoriteArea.getDongName());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbUtil.close(pstmt, conn);
        }
    }

    @Override
    public void deleteFavoriteArea(int favoriteAreaId) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = dbUtil.getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("DELETE FROM favorite_areas WHERE favorite_area_id = ?");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setInt(1, favoriteAreaId);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbUtil.close(pstmt, conn);
        }
    }

    @Override
    public List<FavoriteArea> getFavoriteAreasByUserId(int userInfoId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<FavoriteArea> favoriteAreas = new ArrayList<>();

        try {
            conn = dbUtil.getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM favorite_areas WHERE user_info_id = ?");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setInt(1, userInfoId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                FavoriteArea favoriteArea = new FavoriteArea();
                favoriteArea.setFavoriteAreaId(rs.getInt("favorite_area_id"));
                favoriteArea.setUserInfoId(rs.getInt("user_info_id"));
                favoriteArea.setSidoName(rs.getString("sido_name"));
                favoriteArea.setGunguName(rs.getString("gungu_name"));
                favoriteArea.setDongName(rs.getString("dong_name"));

                favoriteAreas.add(favoriteArea);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbUtil.close(rs, pstmt, conn);
        }

        return favoriteAreas;
    }

    public List<HouseDeal> searchDealsByFavoriteArea(String sidoName, String gunguName, String dongName) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<HouseDeal> houseDealList = new ArrayList<>();

        try {
            conn = dbUtil.getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT d.no, d.apt_seq, apt_dong, floor, deal_year, deal_month, deal_day, ");
            sql.append("exclu_use_ar, deal_amount, i.sgg_cd, i.umd_cd, jibun, road_nm_sgg_cd, road_nm, ");
            sql.append("road_nm_bonbun, road_nm_bubun, apt_nm, build_year, latitude, longitude ");
            sql.append("FROM housedeals d ");
            sql.append("JOIN houseinfos i ON i.apt_seq = d.apt_seq ");
            sql.append("JOIN dongcodes dc ON LEFT(dc.dong_code, 5) = i.sgg_cd AND RIGHT(dc.dong_code, 5) = i.umd_cd ");
            sql.append("WHERE dc.sido_name = ? AND dc.gugun_name = ? AND dc.dong_name = ? ");
            sql.append("LIMIT 20");

            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, sidoName);
            pstmt.setString(2, gunguName);
            pstmt.setString(3, dongName);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                HouseDeal houseDeal = new HouseDeal();
                houseDeal.setNo(rs.getInt("no"));
                houseDeal.setAptSeq(rs.getString("apt_seq"));
                houseDeal.setAptDong(rs.getString("apt_dong"));
                houseDeal.setFloor(rs.getString("floor"));
                houseDeal.setDealYear(rs.getInt("deal_year"));
                houseDeal.setDealMonth(rs.getInt("deal_month"));
                houseDeal.setDealDay(rs.getInt("deal_day"));
                houseDeal.setExcluUseAr(rs.getFloat("exclu_use_ar"));
                houseDeal.setDealAmount(rs.getString("deal_amount"));
                houseDeal.setSggCd(rs.getString("sgg_cd"));
                houseDeal.setUmdCd(rs.getString("umd_cd"));
                houseDeal.setJibun(rs.getString("jibun"));
                houseDeal.setRoadNmSggCd(rs.getString("road_nm_sgg_cd"));
                houseDeal.setRoadNm(rs.getString("road_nm"));
                houseDeal.setRoadNmBonbun(rs.getString("road_nm_bonbun"));
                houseDeal.setRoadNmBubun(rs.getString("road_nm_bubun"));
                houseDeal.setAptNm(rs.getString("apt_nm"));
                houseDeal.setBuildYear(rs.getInt("build_year"));
                houseDeal.setLatitude(rs.getString("latitude"));
                houseDeal.setLongitude(rs.getString("longitude"));

                houseDealList.add(houseDeal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbUtil.close(rs, pstmt, conn);
        }

        return houseDealList;
    }
}
