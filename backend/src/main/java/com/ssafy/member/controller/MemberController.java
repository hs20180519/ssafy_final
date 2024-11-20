package com.ssafy.member.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.request.PasswordRequest;
import com.ssafy.member.model.service.MemberService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/member")
@AllArgsConstructor
@Tag(name = "Member", description = "회원 관련 API")
public class MemberController {
	private final MemberService memberService;

	@Operation(summary = "비밀번호 확인", description = "회원 정보 수정 전 비밀번호를 확인합니다.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "비밀번호 확인 성공"),
		@ApiResponse(responseCode = "500", description = "서버 오류 - 비밀번호 확인 중 오류 발생")
	})
	@PostMapping("/verify-password")
	public ResponseEntity<String> verifyPassword(@RequestBody
	PasswordRequest passwordRequest, Authentication authentication) {
		try {
			// 현재 로그인된 사용자 ID 가져오기
			String memberId = (String)authentication.getPrincipal();
			// 사용자 정보 조회
			MemberDto memberDto = memberService.findMemberByMemberId(memberId);

			if (memberDto == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("사용자를 찾을 수 없습니다.");
			}

			String password = passwordRequest.getPassword();
			System.out.println(password + " " + memberDto.getPassword());
			// 비밀번호 검증
			if (memberService.verifyPassword(password, memberDto.getPassword())) {
				return ResponseEntity.ok("비밀번호 확인 성공");
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("비밀번호가 일치하지 않습니다.");
			}
		} catch (Exception e) {
			// 예외 처리
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류 - 비밀번호 확인 중 오류 발생");
		}
	}

	@Operation(summary = "회원 정보 수정", description = "회원 정보를 수정합니다.")
	@ApiResponses({@ApiResponse(responseCode = "200", description = "회원 정보 수정 성공"),
		@ApiResponse(responseCode = "500", description = "서버 오류 - 회원 정보 수정 중 오류 발생")})
	@PutMapping()
	public ResponseEntity<MemberDto> updateMember(@RequestBody
	MemberDto memberDto, Authentication authentication) {
		try {
			String role = authentication.getAuthorities().stream()
				.map(authority -> authority.getAuthority())
				.findFirst()
				.map(r -> r.replace("ROLE_", "")) // ROLE_ 제거
				.orElse("No Authority");

			memberDto.setId((String)authentication.getPrincipal());
			memberDto.setRole(role);

			System.out.println(memberDto);
			memberService.updateMember(memberDto);
			return ResponseEntity.ok(memberDto);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@Operation(summary = "회원 정보 조회", description = "회원 ID로 회원 정보를 조회합니다.")
	@ApiResponses({@ApiResponse(responseCode = "200", description = "회원 정보 조회 성공"),
		@ApiResponse(responseCode = "500", description = "서버 오류 - 회원 정보 조회 중 오류 발생")})
	@GetMapping()
	public ResponseEntity<MemberDto> findMember(Authentication authentication) {
		try {
			String memberId = (String)authentication.getPrincipal();
			MemberDto memberDto = memberService.findMemberByMemberId(memberId);
			//			memberDto.setPassword(null); TODO : 비밀번호는 공개 X
			return ResponseEntity.ok(memberDto);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@Operation(summary = "회원 삭제", description = "회원 ID로 회원 정보를 삭제합니다.")
	@ApiResponses({@ApiResponse(responseCode = "204", description = "회원 삭제 성공"),
		@ApiResponse(responseCode = "404", description = "회원 없음 - 존재하지 않는 회원 ID"),
		@ApiResponse(responseCode = "500", description = "서버 오류 - 삭제 중 오류 발생")})
	@DeleteMapping("/{memberId}")
	public ResponseEntity<String> deleteMember(@PathVariable
	String memberId) {
		try {
			MemberDto memberDto = memberService.findMemberByMemberId(memberId);
			if (memberDto == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("회원이 존재하지 않습니다.");
			}
			memberService.deleteMember(memberId); // 회원 삭제 메소드 호출
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("삭제 중 오류가 발생했습니다.");
		}
	}

	@Operation(summary = "전체 회원 조회", description = "모든 회원 정보를 조회합니다.")
	@ApiResponses({@ApiResponse(responseCode = "200", description = "전체 회원 조회 성공"),
		@ApiResponse(responseCode = "500", description = "서버 오류 - 회원 조회 중 오류 발생")})
	@GetMapping("/member-list")
	public ResponseEntity<List<MemberDto>> getAllMembers() {
		try {
			List<MemberDto> members = memberService.getAllMembers();
			return ResponseEntity.ok(members);
		} catch (SQLException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@Operation(summary = "회원 검색", description = "회원 ID로 회원 정보를 검색합니다.")
	@ApiResponses({@ApiResponse(responseCode = "200", description = "회원 검색 성공"),
		@ApiResponse(responseCode = "404", description = "회원 없음 - 해당 ID의 회원을 찾을 수 없음"),
		@ApiResponse(responseCode = "500", description = "서버 오류 - 회원 검색 중 오류 발생")})
	@GetMapping("/search")
	public ResponseEntity<List<MemberDto>> searchMembers(@RequestParam
	String memberId) {
		try {
			List<MemberDto> members = memberService.searchMembers(memberId);
			if (members.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
			return ResponseEntity.ok(members);
		} catch (SQLException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

}
