package com.ssafy.address.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.address.model.AddressDto;
import com.ssafy.address.model.service.AddressService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/address")
@RequiredArgsConstructor
public class AddressController {

	private final AddressService addressService;

	// 주소지 조회
	@GetMapping
	public ResponseEntity<List<AddressDto>> getAddressByMemberId(@AuthenticationPrincipal
	String memberId) {
		System.out.println(memberId);
		List<AddressDto> addresses = addressService.getAddressByMemberId(memberId);
		return ResponseEntity.ok(addresses);
	}

	// 주소지 추가
	@PostMapping
	public ResponseEntity<String> addAddress(@AuthenticationPrincipal
	String memberId, @RequestBody
	AddressDto addressDto) {
		System.out.println("추가 " + addressDto);
		addressDto.setMemberId(memberId);
		addressService.addAddress(addressDto);
		return ResponseEntity.ok("주소가 추가되었습니다.");
	}

	@PutMapping("/{addressId}")
	public ResponseEntity<String> updateAddress(@AuthenticationPrincipal
	String memberId,
		@PathVariable
		Long addressId,
		@RequestBody
		AddressDto addressDto) {

		System.out.println(addressDto);
		addressDto.setMemberId(memberId);
		addressDto.setAddressId(addressId); // 주소 ID를 DTO에 설정
		addressService.updateAddress(addressDto);
		return ResponseEntity.ok("주소가 수정되었습니다.");
	}

	// 주소지 삭제
	@DeleteMapping("/{addressId}")
	public ResponseEntity<String> deleteAddress(@PathVariable
	Long addressId) {
		addressService.deleteAddress(addressId);
		return ResponseEntity.ok("주소가 삭제되었습니다.");
	}
}
