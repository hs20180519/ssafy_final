package com.ssafy.favoriteProperty.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.favoriteProperty.model.FavoritePropertyDetailsDto;
import com.ssafy.favoriteProperty.model.FavoritePropertyDto;
import com.ssafy.favoriteProperty.model.service.FavoritePropertyService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/favorite-properties")
public class FavoritePropertyController {

	private final FavoritePropertyService favoritePropertyService;

	@PostMapping
	public ResponseEntity<String> addFavoriteProperty(@AuthenticationPrincipal
	String memberId, @RequestBody
	FavoritePropertyDto favoritePropertyDTO) {
		favoritePropertyDTO.setMemberId(memberId);
		favoritePropertyService.addFavoriteProperty(favoritePropertyDTO);
		return ResponseEntity.ok("관심 매물이 추가되었습니다.");
	}

	@GetMapping
	public ResponseEntity<List<FavoritePropertyDetailsDto>> getFavoriteProperties(@AuthenticationPrincipal
	String memberId) {
		// 회원의 관심 매물 정보 가져오기
		List<FavoritePropertyDetailsDto> properties = favoritePropertyService.getFavoriteProperties(memberId);
		return ResponseEntity.ok(properties);
	}

	@DeleteMapping("/{favoritePropertyId}")
	public ResponseEntity<String> deleteFavoriteProperty(@PathVariable
	String favoritePropertyId) {
		favoritePropertyService.deleteFavoriteProperty(favoritePropertyId);
		return ResponseEntity.ok("관심 매물이 삭제되었습니다.");
	}
}