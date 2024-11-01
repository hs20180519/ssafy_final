package com.ssafy.favoriteArea.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.favoriteArea.model.FavoriteAreaDto;
import com.ssafy.favoriteArea.model.service.FavoriteAreaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/favorite-area")
@Tag(name = "FavoriteArea", description = "즐겨찾기 지역 관련 API")
public class FavoriteAreaController {
	private final FavoriteAreaService favoriteAreaService;

	@Operation(summary = "회원의 즐겨찾기 지역 조회", description = "회원 ID를 사용하여 해당 회원의 즐겨찾기 지역 목록을 조회합니다.")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "즐겨찾기 지역 조회 성공"),
			@ApiResponse(responseCode = "404", description = "즐겨찾기 지역이 존재하지 않음"),
			@ApiResponse(responseCode = "500", description = "서버 오류 - 즐겨찾기 지역 조회 중 오류 발생") })
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

	@Operation(summary = "즐겨찾기 지역 추가", description = "회원 ID와 지역 정보를 사용하여 즐겨찾기 지역을 추가합니다.")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "즐겨찾기 지역 추가 성공"),
			@ApiResponse(responseCode = "500", description = "서버 오류 - 즐겨찾기 지역 추가 중 오류 발생") })
	@PostMapping("/{memberId}")
	public ResponseEntity<String> addFavoriteArea(@PathVariable String memberId,
			@RequestBody Map<String, String> request) {
		try {
			request.put("memberId", memberId);
			favoriteAreaService.addFavoriteArea(request);
			return ResponseEntity.status(HttpStatus.CREATED).body("즐겨찾기 지역이 추가되었습니다.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("즐겨찾기 지역 추가 중 오류가 발생했습니다.");
		}
	}

	@Operation(summary = "즐겨찾기 지역 삭제", description = "즐겨찾기 지역 ID를 사용하여 해당 지역을 삭제합니다.")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "즐겨찾기 지역 삭제 성공"),
			@ApiResponse(responseCode = "500", description = "서버 오류 - 즐겨찾기 지역 삭제 중 오류 발생") })
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