package com.ssafy.dongCode.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.dongCode.model.DongCodeDto;
import com.ssafy.dongCode.model.service.DongCodeService;

@RestController
@RequestMapping("/api/dongcode")
public class DongCodeController {
	
	DongCodeService dongCodeService;
	
	public DongCodeController(DongCodeService dongCodeService) {
		this.dongCodeService = dongCodeService;
	}
	
	@GetMapping
	public ResponseEntity<List<DongCodeDto>> getFilters(@RequestParam String parentCode) {
		List<DongCodeDto> list = dongCodeService.getFilters(parentCode);
		return ResponseEntity.ok(list);
	}
}
