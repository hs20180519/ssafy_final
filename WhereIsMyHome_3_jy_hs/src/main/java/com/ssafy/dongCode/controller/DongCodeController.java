package com.ssafy.dongCode.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.dongCode.model.DongCodeDto;
import com.ssafy.dongCode.model.service.DongCodeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/dong-code")
@Tag(name = "Dong Code controller", description = "필터링 조건을 가져오는 DongCode API")
public class DongCodeController {
	
	private final DongCodeService dongCodeService;
	
	@Operation(summary = "필터링 조건 가져오기", description = "상위 필터 선택 조건을 기반으로 해당 동 코드 필터 조건 목록을 가져옵니다.")
	@Parameter(name="parentCode", description = "선택한 조건 코드", example="11")
	@GetMapping
	public ResponseEntity<List<DongCodeDto>> getFilters(@RequestParam String parentCode) {
		List<DongCodeDto> list = dongCodeService.getFilters(parentCode);
		return ResponseEntity.ok(list);
	}
}
