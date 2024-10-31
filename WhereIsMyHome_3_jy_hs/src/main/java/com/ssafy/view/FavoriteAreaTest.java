package com.ssafy.view;

import com.ssafy.model.dto.FavoriteArea;
import com.ssafy.model.dto.HouseDeal;
import com.ssafy.model.service.FavoriteAreaService;
import com.ssafy.model.service.FavoriteAreaServiceImpl;

import java.util.List;

public class FavoriteAreaTest {
    public static void main(String[] args) {
        FavoriteAreaService favoriteAreaService = FavoriteAreaServiceImpl.getInstance();

        // 1-1. 관심 지역 추가
        print("(1) 관심 지역 추가");
        FavoriteArea newArea = new FavoriteArea();
        newArea.setUserInfoId(1); // 사용자의 ID - 존재하는 사용자의 userId를 사용해야 함
        // TODO : userId가 없으면 관심 지역 추가 실패 알림
        newArea.setSidoName("서울특별시");
        newArea.setGunguName("종로구");
        newArea.setDongName("창신동");
        favoriteAreaService.addFavoriteArea(newArea);
        System.out.println("관심 지역 추가 완료\n");

        // 1-2. 관심 지역 하나 더 추가
        print("(2) 관심 지역 추가");
        newArea = new FavoriteArea();
        newArea.setUserInfoId(1); // 사용자의 ID
        newArea.setSidoName("광주광역시");
        newArea.setGunguName("광산구");
        newArea.setDongName("장덕동");
        favoriteAreaService.addFavoriteArea(newArea);
        System.out.println("관심 지역 추가 완료\n");



        // 2. 사용자의 관심 지역 조회
        print("사용자의 관심 지역 조회");
        List<FavoriteArea> areas = favoriteAreaService.getFavoriteAreasByUserId(1);
        for (FavoriteArea area : areas) {
            System.out.println(area);
        }
        System.out.println("조회 완료\n");

        // 3. 관심 지역의 매매가 우선 조회
        print("관심 지역의 매매가 우선 조회");
        for (FavoriteArea area : areas) {
            String sidoName = area.getSidoName();
            String gunguName = area.getGunguName();
            String dongName = area.getDongName();

            // 관심 지역의 거래 정보 검색
            List<HouseDeal> locationDeals = favoriteAreaService.searchDealsByFavoriteArea(sidoName, gunguName, dongName);
            System.out.println("관심 지역: " + sidoName + " " + gunguName + " " + dongName);
            for (HouseDeal deal : locationDeals) {
                System.out.println(deal.toString());
            }
            System.out.println();
        }
        System.out.println("조회 완료\n");



        // 4. 관심 지역 삭제 (두 번째 관심 지역 삭제)
        if (!areas.isEmpty()) {
            print("관심 지역 삭제");
            favoriteAreaService.deleteFavoriteArea(2);
            // TODO : 삭제할 favoriteAreaId가 없으면 삭제 실패 알림
            System.out.println("관심 지역 삭제 완료\n");
        }

        // 5. 사용자의 관심 지역 다시 조회
        print("사용자의 관심 지역 재조회");
        areas = favoriteAreaService.getFavoriteAreasByUserId(1);
        for (FavoriteArea area : areas) {
            System.out.println(area);
        }
        System.out.println("조회 완료");
    }

    /** 테스트를 위한 메세지 출력 메서드 */
    private static void print(String msg) {
        System.out.println("=== " + msg + " ===");
    }


}

