package com.ssafy.view;

import com.ssafy.model.dto.HouseDeal;
import com.ssafy.model.service.HouseDealService;
import com.ssafy.model.service.HouseDealServiceImpl;
import java.util.List;

public class HouseTest {
    public static void main(String[] args) {
        HouseDealService houseDealService = HouseDealServiceImpl.getInstance();

        print("주택 아파트 실거래 정보 등록");
        HouseDeal houseDealDto = new HouseDeal();

        /*
        INSERT INTO housedeals(apt_seq, floor, deal_year, deal_month, deal_day, exclu_use_ar, deal_amount)
        VALUES(
	    '11110-2224', '1', 2000, 2, 10, 39.39, '13,000');
         */
        houseDealDto.setAptSeq("11110-2224");
        houseDealDto.setFloor("1");
        houseDealDto.setDealYear(2000);
        houseDealDto.setDealMonth(2);
        houseDealDto.setDealDay(10);
        houseDealDto.setExcluUseAr(39.39F);
        houseDealDto.setDealAmount("13,000");

        houseDealService.addHouseDeal(houseDealDto);
        System.out.println("정보 추가 완료\n");


        print("전체 주택 아파트 실거래 내역 조회 - 20개씩");
        List<HouseDeal> allDeals = houseDealService.searchListAll();
        for (HouseDeal deal : allDeals) {
            System.out.println(deal.toString());
        }
        System.out.println("조회 완료\n");

        print("위치 조건에 따른 거래 내역 조회: 서울특별시 종로구 창신동 - 20개씩");
        String sidoCode = "11";    // 서울특별시 코드
        String gugunCode = "11110"; // 종로구 코드 (null이면 서울특별시)
        String dongCode = "17400";  // 창신동 코드 (null이면 종로구)

        List<HouseDeal> locationDeals = houseDealService.searchListByLocation(sidoCode, gugunCode, dongCode);
        for (HouseDeal deal : locationDeals) {
            System.out.println(deal.toString());
        }




    }
    /** 테스트를 위한 메세지 출력 메서드 */
    private static void print(String msg) {
        System.out.println("=== " + msg + " ===");
    }

}
