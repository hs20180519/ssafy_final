package com.ssafy.favoriteArea.controller;

import com.ssafy.favoriteArea.model.FavoriteAreaDto;
import com.ssafy.favoriteArea.model.service.FavoriteAreaService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/favorite-area")
public class FavoriteAreaController {
    private final FavoriteAreaService favoriteAreaService;

    public FavoriteAreaController(FavoriteAreaService favoriteAreaService) {
        this.favoriteAreaService = favoriteAreaService;
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<List<FavoriteAreaDto>> getFavoriteAreaByMemberId(@PathVariable String memberId) {
        try {
            List<FavoriteAreaDto> favoriteAreas = favoriteAreaService.findFavoriteAreaByMemberId(memberId);
            if (favoriteAreas != null && !favoriteAreas.isEmpty()) {
                return ResponseEntity.ok(favoriteAreas); // 데이터가 있을 경우 반환
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // 데이터 없음
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // 오류 처리
        }
    }

    @PostMapping("/{memberId}")
    public ResponseEntity<String> addFavoriteArea(@PathVariable String memberId, @RequestBody Map<String, String> request) {
        try {
            request.put("memberId", memberId);
            favoriteAreaService.addFavoriteArea(request);
            return ResponseEntity.status(HttpStatus.CREATED).body("즐겨찾기 지역이 추가되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("즐겨찾기 지역 추가 중 오류가 발생했습니다.");
        }
    }

    @DeleteMapping("/{favoriteAreaId}")
    public ResponseEntity<String> removeFavoriteArea(@PathVariable int favoriteAreaId) {
        try {
            favoriteAreaService.removeFavoriteArea(favoriteAreaId);
            return ResponseEntity.ok("즐겨찾기 지역이 삭제되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("즐겨찾기 지역 삭제 중 오류가 발생했습니다.");
        }
    }
}