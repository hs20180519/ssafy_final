package com.ssafy.model.controller;

import jakarta.servlet.annotation.WebServlet;


import com.ssafy.model.service.HouseDealService; // 서비스를 통해 데이터 접근
import com.ssafy.model.service.HouseDealServiceImpl;
import com.ssafy.model.dto.HouseDeal; // HouseDeal DTO
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/house")
public class HouseDealController extends HttpServlet {
    
    private HouseDealService houseDealService = HouseDealServiceImpl.getInstance(); // 서비스 객체
    private static final long serialVersionUID = 1L;

    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 파라미터 받기
        String sidoCode = request.getParameter("sidoCode");
        String gugunCode = request.getParameter("gugunCode");
        String dongCode = request.getParameter("dongCode");

        System.out.println(sidoCode + " " + gugunCode + " " + dongCode);
        // 데이터 조회
        List<HouseDeal> houseDeals = houseDealService.searchListByLocation(sidoCode, gugunCode, dongCode);

        // JSON 응답 생성
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        // JSON 형식으로 데이터 전송
        String jsonResponse = convertToJson(houseDeals);
        out.print(jsonResponse);
        out.flush();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//톰캣 10버전부터는 post에서 한글 인코딩 안해도 문제 없음
//		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

    // HouseDeal 리스트를 JSON 형식으로 변환하는 메서드
    private String convertToJson(List<HouseDeal> houseDeals) {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("[");

        for (int i = 0; i < houseDeals.size(); i++) {
            HouseDeal deal = houseDeals.get(i);
            jsonBuilder.append("{")
                .append("\"no\":").append(deal.getNo()).append(",")
                .append("\"aptSeq\":\"").append(deal.getAptSeq()).append("\",")
                .append("\"aptDong\":\"").append(deal.getAptDong()).append("\",")
                .append("\"floor\":\"").append(deal.getFloor()).append("\",")
                .append("\"dealYear\":").append(deal.getDealYear()).append(",")
                .append("\"dealMonth\":").append(deal.getDealMonth()).append(",")
                .append("\"dealDay\":").append(deal.getDealDay()).append(",")
                .append("\"excluUseAr\":").append(deal.getExcluUseAr()).append(",")
                .append("\"dealAmount\":\"").append(deal.getDealAmount()).append("\",")
                .append("\"sggCd\":\"").append(deal.getSggCd()).append("\",")
                .append("\"umdCd\":\"").append(deal.getUmdCd()).append("\",")
                .append("\"jibun\":\"").append(deal.getJibun()).append("\",")
                .append("\"roadNmSggCd\":\"").append(deal.getRoadNmSggCd()).append("\",")
                .append("\"roadNm\":\"").append(deal.getRoadNm()).append("\",")
                .append("\"roadNmBonbun\":\"").append(deal.getRoadNmBonbun()).append("\",")
                .append("\"roadNmBubun\":\"").append(deal.getRoadNmBubun()).append("\",")
                .append("\"aptNm\":\"").append(deal.getAptNm()).append("\",")
                .append("\"buildYear\":").append(deal.getBuildYear()).append(",")
                .append("\"latitude\":\"").append(deal.getLatitude()).append("\",")
                .append("\"longitude\":\"").append(deal.getLongitude()).append("\"")
                .append("}");

            if (i < houseDeals.size() - 1) {
                jsonBuilder.append(",");
            }
        }
        jsonBuilder.append("]");

        return jsonBuilder.toString();
    }
}
