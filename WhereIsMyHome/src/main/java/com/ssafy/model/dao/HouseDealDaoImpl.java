package com.ssafy.model.dao;

import com.ssafy.model.dto.HouseDeal;
import com.ssafy.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HouseDealDaoImpl implements HouseDealDao {
	private static DBUtil dbUtil = DBUtil.getInstance();

	private static final HouseDealDao instance = new HouseDealDaoImpl();

	private HouseDealDaoImpl() {

	}

	public static HouseDealDao getInstance() {
		return instance;
	}

	@Override
	public void insertHouseDeal(HouseDeal houseDealDto) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		// INSERT INTO housedeals(apt_seq, floor, deal_year, deal_month, deal_day,
		// exclu_use_ar, deal_amount)
		// VALUES(
		// '11110-2224', '1', 2000, 2, 10, 39.39, '13,000');
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append(
					"INSERT INTO housedeals(apt_seq, floor, deal_year, deal_month, deal_day, exclu_use_ar, deal_amount) \n");
			sql.append("VALUES");
			sql.append("(?, ?, ?, ?, ?, ?, ?)");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, houseDealDto.getAptSeq());
			pstmt.setString(2, houseDealDto.getFloor());
			pstmt.setInt(3, houseDealDto.getDealYear());
			pstmt.setInt(4, houseDealDto.getDealMonth());
			pstmt.setInt(5, houseDealDto.getDealDay());
			pstmt.setFloat(6, houseDealDto.getExcluUseAr());
			pstmt.setString(7, houseDealDto.getDealAmount());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}

	@Override
	public List<HouseDeal> selectList() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<HouseDeal> houseDealList = new ArrayList<>();

		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();

			// 전체 거래 내역 조회를 위한 SQL 쿼리
			sql.append("SELECT d.no, d.apt_seq, apt_dong, floor, deal_year, deal_month, deal_day, ");
			sql.append("exclu_use_ar, deal_amount, i.sgg_cd, i.umd_cd, jibun, road_nm_sgg_cd, road_nm, ");
			sql.append("road_nm_bonbun, road_nm_bubun, apt_nm, build_year, latitude, longitude ");
			sql.append("FROM housedeals d ");
			sql.append("JOIN houseinfos i ON i.apt_seq = d.apt_seq ");
			sql.append("LIMIT 20"); // 20개만

			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();

			// 결과 처리
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

				houseDealList.add(houseDeal); // 리스트에 결과 추가
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}

		return houseDealList; // 결과 리스트 반환
	}
	
	@Override
	public List<HouseDeal> selectRecentlyList(String dealYear) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<HouseDeal> houseDealList = new ArrayList<>();

		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();

			// 전체 거래 내역 조회를 위한 SQL 쿼리
			sql.append("SELECT d.no, d.apt_seq, apt_dong, floor, deal_year, deal_month, deal_day, ");
			sql.append("exclu_use_ar, deal_amount, i.sgg_cd, i.umd_cd, jibun, road_nm_sgg_cd, road_nm, ");
			sql.append("road_nm_bonbun, road_nm_bubun, apt_nm, build_year, latitude, longitude ");
			sql.append("FROM housedeals d ");
			sql.append("JOIN houseinfos i ON i.apt_seq = d.apt_seq ");
			sql.append("WHERE deal_year = ?");
			sql.append("LIMIT 500"); // 20개만

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, dealYear);
			rs = pstmt.executeQuery();

			// 결과 처리
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

				houseDealList.add(houseDeal); // 리스트에 결과 추가
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}

		return houseDealList; // 결과 리스트 반환
	}
	
	@Override
	public List<String> selectHouseInfo(String keyword) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<String> houseNameList = new ArrayList<>();

		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();

			// 전체 거래 내역 조회를 위한 SQL 쿼리
			sql.append("SELECT apt_nm ");
			sql.append("FROM houseinfos ");
			sql.append("WHERE apt_nm LIKE ?");
//			sql.append("LIMIT 20"); // 20개만

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, "%" + keyword + "%");
			rs = pstmt.executeQuery();

			// 결과 처리
			while (rs.next()) {
				HouseDeal houseDeal = new HouseDeal();
				houseNameList.add(rs.getString("apt_nm"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}

		return houseNameList; // 결과 리스트 반환
	}


	@Override
	public List<HouseDeal> selectByLocation(String sidoCode, String gugunCode, String dongCode) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<HouseDeal> houseDealList = new ArrayList<>();

		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();

			// SQL 쿼리 작성
			sql.append("SELECT d.no, d.apt_seq, apt_dong, floor, deal_year, deal_month, deal_day, ");
			sql.append("exclu_use_ar, deal_amount, i.sgg_cd, i.umd_cd, jibun, road_nm_sgg_cd, road_nm, ");
			sql.append("road_nm_bonbun, road_nm_bubun, apt_nm, build_year, latitude, longitude ");
			sql.append("FROM housedeals d ");
			sql.append("JOIN houseinfos i ON i.apt_seq = d.apt_seq ");
			sql.append("WHERE i.sgg_cd LIKE ? "); // 시 코드는 필수 조건
			if (gugunCode != null && !gugunCode.isEmpty()) {
				sql.append("AND i.sgg_cd LIKE ? "); // 구 코드 조건 (존재하면 추가)
			}
			if (dongCode != null && !dongCode.isEmpty()) {
				sql.append("AND i.umd_cd = ? "); // 동 코드 조건 (존재하면 추가)
			}
			sql.append("LIMIT 20"); // 20개만

			pstmt = conn.prepareStatement(sql.toString());

			// 시도 코드 바인딩
			pstmt.setString(1, sidoCode + "%"); // 시도 코드
			if (gugunCode != null && !gugunCode.isEmpty()) {
				pstmt.setString(2, "%" + gugunCode); // 구 코드
			}
			if (dongCode != null && !dongCode.isEmpty()) {
				pstmt.setString(3, dongCode); // 동 코드
			}

			rs = pstmt.executeQuery();

			// 결과 처리
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

				houseDealList.add(houseDeal); // 리스트에 결과 추가
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}

		return houseDealList; // 결과 리스트 반환
	}

}
